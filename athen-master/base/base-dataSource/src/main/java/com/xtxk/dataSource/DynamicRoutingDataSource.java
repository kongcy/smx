package com.xtxk.dataSource;

import com.xtxk.core.util.ContextHolder;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.dataSource.provider.DataSourceProvider;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 动态数据源
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/30
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {
    @Setter
    private DataSourceProvider provider;
    @Setter
    private String primary;
    @Setter
    private boolean strict;
    private boolean p6spy;
    private Map<String, DataSource> dataSourceHashMap = new LinkedHashMap<>();
    /**
     * 获取数据源
     * @return DataSource
     */
    @Override
    protected DataSource getDataSource() {
        return findDataSource(ContextHolder.peek());
    }

    public DataSource findDataSource(String ds) {
        DataSource dataSource = dataSourceHashMap.get(ds);
        if (U.isBlank(dataSource)) {
            return findDefaultDataSource();
        }
        return dataSource;
    }

    /**
     * 获取默认数据源
     *
     * @return DataSource
     */
    public DataSource findDefaultDataSource() {
        return dataSourceHashMap.get(primary);
    }

    /**
     * 添加数据源
     *
     * @param ds         数据源名称
     * @param dataSource 数据源
     */
    public synchronized void saveDataSource(String ds, DataSource dataSource) {
        dataSourceHashMap.put(ds, dataSource);
        LogUtil.LOG.info("动态数据源-加载 {} 成功", ds);
    }

    @Override
    public void destroy() throws Exception {
        LogUtil.LOG.info("closing dynamicDatasource  ing....");
        for (Map.Entry<String, DataSource> item : dataSourceHashMap.entrySet()) {
            DataSource dataSource = item.getValue();
            Class<? extends DataSource> clazz = dataSource.getClass();
            try {
                Method closeMethod = clazz.getDeclaredMethod("close");
                closeMethod.invoke(dataSource);
            } catch (NoSuchMethodException e) {
                LogUtil.LOG.error("关闭数据源 {} 失败,", item.getKey());
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, DataSource> dataSourceMap = provider.loadDataSource();
        if (LogUtil.LOG.isInfoEnabled()) {
            LogUtil.LOG.info("初始共加载 {} 个数据源", dataSourceMap.size());
        }
        for (Map.Entry<String, DataSource> ds : dataSourceMap.entrySet()) {
            saveDataSource(ds.getKey(), ds.getValue());
        }
    }

    public String getPrimary() {
        return primary;
    }
}
