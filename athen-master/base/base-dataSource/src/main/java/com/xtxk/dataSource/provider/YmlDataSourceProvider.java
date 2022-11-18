package com.xtxk.dataSource.provider;

import com.xtxk.core.util.LogUtil;
import com.xtxk.dataSource.creator.DataSourceCreator;
import com.xtxk.dataSource.model.DataSourceConfig;
import com.xtxk.dataSource.model.DataSourceProperty;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 从配置文件加载
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/30
 */
public class YmlDataSourceProvider implements DataSourceProvider {
    private DataSourceConfig config;
    private DataSourceCreator creator;

    public YmlDataSourceProvider(DataSourceCreator creator, DataSourceConfig config) {
        this.creator = creator;
        this.config = config;
    }

    /**
     * 加载数据源
     */
    @Override
    public Map<String, DataSource> loadDataSource() {
        Map<String, DataSourceProperty> propertyMap = config.getGroup();
        Map<String, DataSource> ds = new HashMap<>();
        if (propertyMap != null) {
            for (String key : propertyMap.keySet()) {
                DataSourceProperty property = propertyMap.get(key);
                DataSource dataSource = null;
                try {
                    dataSource = creator.createDataSource(property,config.getDruid());
                } catch (ClassNotFoundException e) {
                    LogUtil.LOG.error("加载数据源失败");
                }
                ds.put(key, dataSource);
            }
        }
        return ds;
    }
}
