package com.athen.sync.web;

import com.athen.sync.model.Column;
import com.athen.sync.util.CommonUtil;
import com.athen.sync.util.Mysql2OracleUtil;
import com.athen.sync.util.Oracle2MysqlUtil;
import com.athen.sync.util.Result;
import com.xtxk.core.date.DateFormatType;
import com.xtxk.core.date.DateUtil;
import com.xtxk.core.util.LogUtil;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/datasync")
public class IndexController implements BeanNameAware {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1; //主数据库

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;//源数据库

    @Value("${spring.datasource.primary.name}")
    public String primaryName;   //本地数据库名

    @Value("${spring.datasource.secondary.name}")
    public String secondaryName;  //源数据库名

    @Value("${spring.datasource.primary.type}")
    public String primaryDBType;   //本地数据库类型

    @Value("${spring.datasource.secondary.type}")
    public String secondaryDBType;  //源数据库类型


    private String doubleOracle = "doubleOracle";//两个数据库都是oracle数据库
    private String doubleMysql = "doubleMysql";//两个数据库都是mysql数据库
    private String oracleAndMysql = "oracleAndMysql";//primaryDB是oralce，secondaryDB是mysql
    private String mysqlAndOracle = "mysqlAndOracle";//primaryDB是mysql，secondaryDB是oralce

    private static final String PRIMARY = "PRIMARY";
    private static final String COUNT = "COUNT";
    //成功时0
    private int SYNC_TABLE_SUCCESS_CODE = 0;
    //备份表时表名过长
    private int BK_TABLE_TOOLONG_CODE = 1;
    //新建表时报错
    private int NEW_TABLE_ERROR_CODE = 2;
    //插入数据时报错
    private int INSERT_TABLE_ERROR_CODE = 3;


    private String beanName;//获取当前类名称

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        System.err.println("成功访问！");
        String sql = null;
        if ("oracle".equalsIgnoreCase(secondaryDBType)) {
            sql = "SELECT TABLE_NAME FROM USER_TAB_COMMENTS";
        } else if ("mysql".equalsIgnoreCase(secondaryDBType)) {
            sql = "show tables";
        } else {

        }
        ArrayList<String> tableList = screenData(sql);
        request.setAttribute("tables", tableList);
        return "index";
    }

    @RequestMapping("/seach")
    @ResponseBody
    public Object seach(String condition, HttpServletRequest request) {
        String sql = null;
        if ("oracle".equalsIgnoreCase(secondaryDBType)) {
            sql = "SELECT TABLE_NAME FROM USER_TAB_COMMENTS";
            if (null != condition) {
                condition = condition.toUpperCase();
                sql += " WHERE TABLE_NAME LIKE '%" + condition + "%'";
            }
        } else if ("mysql".equalsIgnoreCase(secondaryDBType)) {
            sql = "select table_name from (select table_name from information_schema.tables where table_schema='" + secondaryName + "') t";
            if (null != condition) {
                sql += " where t.table_name like '%" + condition + "%'";
            }
        } else {

        }
        ArrayList<String> tableList = screenData(sql);
        request.setAttribute("seachTableList", tableList);
        return Result.success("成功", tableList);
    }

    /**
     * 筛选数据
     *
     * @Author: WS-
     * @return:
     * @Date: 2019/5/7  16:48
     * @Description:
     */
    private ArrayList<String> screenData(String sql) {
        List<Map<String, Object>> maps = jdbcTemplate2.queryForList(sql);
        ArrayList<String> tableList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            for (Object value : map.values()) {
                tableList.add(value.toString());
            }
        }
        return tableList;
    }

    /**
     * 同步整表数据
     *
     * @param tableName 表名称
     * @Author: WS-
     * @return:
     * @Date: 2019/7/1  16:30
     * @Description:
     */
    @RequestMapping(value = "/doSyncData", method = RequestMethod.POST)
    @ResponseBody
    public Result doSyncData(@RequestParam("tableName") String tableName) {
        try {
            //查询本地数据库中是否存在该表，不存在则直接新建表，存在则备份
            int count = checkTable(jdbcTemplate1,tableName);
            if (count == 0) {
                LogUtil.LOG.info("{}:不存在表：{}", beanName, tableName);
                //本地数据库不存在该表，直接新建表
                LogUtil.LOG.info("{}:开始新建表:{}", beanName, tableName);
                try {
                    createTable(jdbcTemplate1,jdbcTemplate2,tableName);
                } catch (Exception e) {
                    return Result.error(NEW_TABLE_ERROR_CODE, "重建表失败");
                }
            } else {
                //1、修改目标表名（备份）
                LogUtil.LOG.info("{}:存在表:{}", beanName, tableName);
                LogUtil.LOG.info("{}:开始备份表:{}", beanName, tableName);
                try {
                    backUpTable(jdbcTemplate1,tableName);
                } catch (Exception e) {
                    LogUtil.LOG.error("表【"+tableName+"】备份出错=========> "+e.getLocalizedMessage());
                    return Result.error(BK_TABLE_TOOLONG_CODE, "表名过长，备份表失败");
                }
            }
            //3、插入数据
            LogUtil.LOG.info("{}:开始更新，往表:{}中插入数据", beanName, tableName);
            int dataCount = insertDataToTable(null,null,tableName);
            LogUtil.LOG.info("{}:同步成功，往表:{}插入了{}条数据", beanName, tableName, dataCount);
            return Result.success("同步成功");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.LOG.info("{}:同步:{} 表失败", beanName, tableName);
            //return ResultUtil.error("同步失败");
            return Result.error("同步失败");
        }
    }

    /**
     * 追加数据到现有表中，如果表中数据存在重复的则更新该条数据
     *
     * @param tableName
     * @Author: WS-
     * @return:
     * @Date: 2019/6/4  14:19
     * @Description:
     */
    @RequestMapping(value = "/doAddtoData", method = RequestMethod.POST)
    @ResponseBody
    public Object doAddtoData(@RequestParam("tableName") String tableName) {
        //1、检测是否存在表
        int i = checkTable(jdbcTemplate1,tableName);
        if (i < 0) {
            return Result.error(1, "本地数据库不存在表：" + tableName);
        } else {
            //2、获取两个数据的同张表中所有数据
            String sql = "select * from " + tableName;
            List<Map<String, Object>> mapList1 = jdbcTemplate1.queryForList(sql);
            List<Map<String, Object>> mapList2 = jdbcTemplate2.queryForList(sql);
            String secondId = null;
            //记录更新的条数
            int updateCount = 0;
            int insertCount = 0;
            //获取目标表id值
            for (Map<String, Object> map : mapList2) {
                for (String key : map.keySet()) {
                    if (key.equalsIgnoreCase("id")) {
                        //查询id
                        secondId = (String) map.get(key);
                        //根据id查询该条记录是否已经存在
                        String queryByIdSql = "select * from " + tableName + " where id ='" + secondId + "'";
                        Map<String, Object> map1 = null;
                        List<Map<String, Object>> fildCountList = CommonUtil.getFildCountList(tableName, primaryDBType, secondaryDBType, secondaryName,jdbcTemplate2);
                        try {
                            map1 = jdbcTemplate1.queryForMap(queryByIdSql);
                            if (map1.size() > 0) {
                                //如果存在则更新
                                //获取字段数量
                                int count = updateInAddtoData(fildCountList, tableName, secondId, map);
                                updateCount += count;
                                LogUtil.LOG.info("{}：更新了{}条数据",beanName, updateCount);
                            } else {
                                //不存在则添加
                                int count = insertInAddToData(fildCountList, tableName, secondId);
                                insertCount += count;
                                LogUtil.LOG.info("{}：插入了{}条数据",beanName, insertCount);
                            }
                        } catch (DataAccessException e) {
                            List<Map<String, Object>> mapList = jdbcTemplate1.queryForList(queryByIdSql);
                            if (mapList.size() > 0) {
                                //存在
                                int count = updateInAddtoData(fildCountList, tableName, secondId, map);
                                updateCount += count;
                                LogUtil.LOG.info("更新了{}条数据", updateCount);
                            } else {
                                //不存在
                                int count = insertInAddToData(fildCountList, tableName, secondId);
                                insertCount += count;
                                LogUtil.LOG.info("插入了{}条数据", insertCount);
                            }
                        }
                    } else {
                        //寻找主键

                    }
                }
            }
        }
        return Result.success("同步成功");
    }

    /**
     * 创建表
     *
     * @Author: WS-
     * @return:
     * @Date: 2019/4/26  15:41
     * @Description:
     * @param primaryJdbcTemplate 本地数据库
     * @param secondaryJdbcTemplate 服务器数据库
     */
    private void createTable(JdbcTemplate primaryJdbcTemplate,JdbcTemplate secondaryJdbcTemplate,String tableName) {
        String getDDLSql = null;
        //获取建表语句
        if ("oracle".equalsIgnoreCase(secondaryDBType)) {
            getDDLSql = "SELECT DBMS_METADATA.GET_DDL('TABLE','" + tableName + "','" + secondaryName + "') AS  DDL FROM DUAL";
        } else if ("mysql".equalsIgnoreCase(secondaryDBType)) {
            getDDLSql = "show create table " + tableName;
        } else {

        }
        List<Map<String, Object>> maps = secondaryJdbcTemplate.queryForList(getDDLSql);
        String ddl = null;
        String createTableSql = null;
        String DBType = checkDBType(primaryDBType, secondaryDBType);

        //两个数据库都是oracle
        if (DBType.equalsIgnoreCase(doubleOracle)) {
            ddl = maps.get(0).get("DDL").toString();
            //将源数据库中数据名改为本地数据库名
            createTableSql = ddl.replaceAll(secondaryName, primaryName);
        }
        //两个数据库都是mysql
        else if (DBType.equalsIgnoreCase(doubleMysql)) {
            ddl = maps.get(0).get("Create Table").toString();
            //将源数据库中数据名改为本地数据库名
            createTableSql = ddl.replaceAll(secondaryName, primaryName);
        }
        //本地数据库是oracle，源数据库是mysql
        else if (DBType.equalsIgnoreCase(oracleAndMysql)) {
            ArrayList<Column> mysqlColumn = Mysql2OracleUtil.getMysqlColumn(tableName, secondaryJdbcTemplate);
            createTableSql = Mysql2OracleUtil.mysqlDDL2OracleDDL(tableName, mysqlColumn).toString();
        }
        //本地数据库是mysql，源数据库是oracle
        else if (DBType.equalsIgnoreCase(mysqlAndOracle)) {
            //获取源数据库oracle的字段和字段长度等信息
            List<Column> oracleColumn = Oracle2MysqlUtil.getOracleColumn(tableName, secondaryJdbcTemplate);
            //将oracle建表语句改成mysql建表语句
            createTableSql = Oracle2MysqlUtil.oracleDDL2Mysql(tableName, oracleColumn).toString();
        } else {
            LogUtil.LOG.info("{}:数据库类型无法判断！", beanName);
        }

        primaryJdbcTemplate.execute(createTableSql);
        LogUtil.LOG.info("{}:新建表:{}成功", beanName, tableName);
    }

    /**
     * 备份表
     *
     * @Author: WS-
     * @return:
     * @Date: 2019/4/26  15:42
     * @Description:
     */
    private void backUpTable(JdbcTemplate primaryJdbcTemplate,String tableName) throws Exception {
        //获取当前时间，并修改成yyyy-MM_dd_HH_mm_ss模式
        String defaultDateStr = DateUtil.format(new Date(), DateFormatType.YYYYMMDD);
        //以时间毫秒值为结尾进行表备份
        String newName = tableName + "_BK_" + defaultDateStr;
        //防止oracle表名超过30位报错
        if ("oracle".equalsIgnoreCase(primaryDBType)) {
            if (tableName.length() > 30) {
                throw new Exception("表名过长，请手动备份！");
            }
            String tempName = tableName + "_BK_";
            String bkTime = defaultDateStr + "";
            if (newName.length() > 30) {
                int bkLength = 30 - tempName.length();
                String end = bkTime.substring(bkLength);
                newName = tableName + end;
            }
        }
        String deleteStr = "drop table "+newName;
        String createStr = "CREATE TABLE "+newName+" LIKE "+tableName+";";
        String copyStr="INSERT INTO "+newName+" SELECT * FROM "+tableName+";";
     //   String renameTableSql = "ALTER TABLE " + tableName + " RENAME TO " + newName;
        try{
            int check = checkTable(primaryJdbcTemplate,newName);
            if(check>0){
                primaryJdbcTemplate.execute(deleteStr);
            }
            primaryJdbcTemplate.execute(createStr);
            LogUtil.LOG.info("{}:备份表:{}成功，新表名为:{}", beanName, tableName, newName);
        }catch (Exception e){
            LogUtil.LOG.error("表【"+tableName+"】备份出错=========> "+e.getLocalizedMessage());
        }
        LogUtil.LOG.info("===========================开始导入数据到备份表中！=======================");
        primaryJdbcTemplate.execute(copyStr);
    }

    /**
     * 将数据插入到表中
     *
     * @Author: WS-
     * @return:
     * @Date: 2019/4/26  15:44
     * @Description:
     */
    private int insertDataToTable(JdbcTemplate primaryJdbcTemplate,JdbcTemplate secondaryJdbcTemplate, String tableName) {
        String getDataListSql = "SELECT * FROM " + tableName;
        if ("mysql".equalsIgnoreCase(secondaryDBType)) {
            getDataListSql = getDataListSql.toLowerCase();
        }
        List<Map<String, Object>> list = jdbcTemplate2.queryForList(getDataListSql);
        List<Map<String, Object>> fildCountList = CommonUtil.getFildCountList(tableName, primaryDBType, secondaryDBType,secondaryName, jdbcTemplate2);
        int size = fildCountList.size();
        String temp = "";
        for (int i = 0; i < size; i++) {
            temp += "?,";
        }
        temp = temp.substring(0, temp.lastIndexOf(","));
        String insertSql = "INSERT INTO " + tableName + " VALUES(" + temp + ")";
        int count = 0;
        Map<String,String> paramMap = findPrimary(tableName);
        for (Map<String, Object> map : list) {
            ArrayList<Object> tempList = new ArrayList<>();
            StringBuffer buffer = new StringBuffer();
            buffer.append("SELECT count(*) AS COUNT FROM ").append(tableName).append(" WHERE ");
            boolean isAdd = true;
            for(Map.Entry<String,Object> entry: map.entrySet()){
                String key = entry.getKey();
                Object value = entry.getValue();
                if(key!=null&&paramMap.get(PRIMARY).equalsIgnoreCase(key)){
                    buffer.append(key).append("=").append("'"+value.toString()+"'");
                    String selectById = buffer.toString();
                    List<Map<String, Object>> resultlist=primaryJdbcTemplate.queryForList(selectById);
                    Map<String,Object> resultMap = resultlist.get(0);
                    Integer c = Integer.valueOf(resultMap.get(COUNT).toString());
                    if(c>0){
                        isAdd=false;
                    }
                }
                if(isAdd){
                    tempList.add(value);
                }
            }
            Object[] obj = tempList.toArray();
            if(obj.length>0){
                count++;
              //  int update = jdbcTemplate.update(insertSql, obj);
            }
        }
        return count;
    }

    /**
     * 查找主键
     * @param tableName 表名
     * */
    private Map<String,String> findPrimary(String tableName){
          String selectSql = "SHOW INDEX FROM "+tableName;
          List<Map<String,Object>> resultlist = jdbcTemplate1.queryForList(selectSql);
        Map<String,String> params  = new HashMap<>();
          for(Map<String,Object> map:resultlist){
               if(map.get("Key_name").toString().equals(PRIMARY)){
                   String obj = map.get("Column_name").toString();
                   params.put(PRIMARY,obj);
               }
          }
          System.out.println(resultlist);
          return params;
    }

    /**
     * @Author: WS-
     * @return:
     * @Date: 2019/4/26  15:54
     * @Description: 校验表是否存在
     */
    private int checkTable(JdbcTemplate jdbcTemplate,String tableName) {
        String checkTableSql = null;
        if ("oracle".equalsIgnoreCase(primaryDBType)) {
            checkTableSql = "SELECT COUNT(*) AS C FROM USER_TABLES WHERE TABLE_NAME = '" + tableName.toUpperCase() + "'";
        } else if ("mysql".equalsIgnoreCase(primaryDBType)) {
            checkTableSql = "select count(*) as C from (select table_name from information_schema.tables where table_schema='" + primaryName + "') t where t.table_name = '" + tableName + "'";
        } else {

        }
        List<Map<String, Object>> listMap = jdbcTemplate.queryForList(checkTableSql);
        String c = listMap.get(0).get("C").toString();
        Integer count = Integer.valueOf(c);
        return count;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        //List<Column> test = Oracle2MysqlUtil.getOracleColumn("TEST", jdbcTemplate2);
        ArrayList<Column> two = Mysql2OracleUtil.getMysqlColumn("two", jdbcTemplate2);
    }


    /**
     * 确认本地数据库和源数据库
     *
     * @param primaryDBType   本地数据库
     * @param secondaryDBType 源数据库
     * @Author: WS-
     * @return:
     * @Date: 2019/5/13  14:49
     * @Description:
     */
    private String checkDBType(String primaryDBType, String secondaryDBType) {
        //两个数据库都是oracle
        if ("oracle".equalsIgnoreCase(primaryDBType) && "oracle".equalsIgnoreCase(secondaryDBType)) {
            return "doubleOracle";
        }
        //两个数据库都是mysql
        else if ("mysql".equalsIgnoreCase(primaryDBType) && "mysql".equalsIgnoreCase(secondaryDBType)) {
            return "doubleMysql";
        }
        //primaryDB是oracle，secondaryDB是mysql
        else if ("oracle".equalsIgnoreCase(primaryDBType) && "mysql".equalsIgnoreCase(secondaryDBType)) {
            return "oracleAndMysql";
        }
        //primaryDB是mysql，secondaryDB是oracle
        else if ("mysql".equalsIgnoreCase(primaryDBType) && "oracle".equalsIgnoreCase(secondaryDBType)) {
            return "mysqlAndOracle";
        } else {
            return "error";
        }
    }

    /**
     *
     * @Author:  WS-
     * @param fildCountList
     * @param tableName
     * @param secondId
     * @param map
     * @return:  int
     * @Date:    2019/7/5  11:19
     * @Description:
     */
    private int updateInAddtoData(List<Map<String, Object>> fildCountList, String tableName, String secondId, Map<String, Object> map) {
        String updateSetStr = "";
        for (Map<String, Object> countMap : fildCountList) {
            for (String columnKey : countMap.keySet()) {
                String column = countMap.get(columnKey).toString();
                if (!column.equalsIgnoreCase("id")) {
                    updateSetStr += column + "= '" + map.get(column) + "',";
                }
            }
        }
        updateSetStr = updateSetStr.substring(0, updateSetStr.lastIndexOf(","));
        String updateByIdSql = "update " + tableName + " set " + updateSetStr + " where id='" + secondId + "'";
        int count = jdbcTemplate1.update(updateByIdSql);
        return count;
    }
    /**
     * @Author:  WS-
      * @param null
     * @return:
     * @Date:    2019/7/1  17:15
     * @Description: 追加数据时用来新增数据使用
     */
    private int insertInAddToData(List<Map<String, Object>> fildCountList, String tableName, String secondId) {
        String getDataById = "SELECT * FROM " + tableName + " WHERE ID ='" + secondId + "'";
        List<Map<String, Object>> list = jdbcTemplate2.queryForList(getDataById);
        int size = fildCountList.size();
        String temp = "";
        for (int j = 0; j < size; j++) {
            temp += "?,";
        }
        temp = temp.substring(0, temp.lastIndexOf(","));
        String insertSql = "INSERT INTO " + tableName + " VALUES(" + temp + ")";
        int count = 0;
        for (Map<String, Object> tempmap : list) {
            ArrayList<Object> tempList = new ArrayList<>();
            for (Object value : tempmap.values()) {
                tempList.add(value);
            }
            Object[] obj = tempList.toArray();
            int update = jdbcTemplate1.update(insertSql, obj);
            count += update;
        }
        return count;
    }
}
