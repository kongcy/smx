package com.xtxk.dataSource.creator;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.xtxk.core.util.LogUtil;
import com.xtxk.dataSource.model.DataSourceConfig;
import com.xtxk.dataSource.model.DataSourceProperty;
import com.xtxk.dataSource.model.DruidConfig;
import com.xtxk.dataSource.toolkit.DruidWallConfigUtil;
import lombok.Setter;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description 数据源创建类
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/31
 */
public class DataSourceCreator {
    /**
     * DRUID数据源类
     */
    private static final String DRUID_DATASOURCE = "com.alibaba.druid.pool.DruidDataSource";
    /**
     * HikariCp数据源
     */
    private static final String HIKARI_DATASOURCE = "com.zaxxer.hikari.HikariDataSource";
    /**
     * JNDI数据源查找
     */
    private static final JndiDataSourceLookup JNDI_DATA_SOURCE_LOOKUP = new JndiDataSourceLookup();
    /**
     * 是否存在druid
     */
    private Boolean druidExists = false;
    /**
     * 是否存在hikari
     */
    private Boolean hikariExists = false;
    @Setter
    private DruidConfig druidGlobalConfig;
    @Setter
    private WebApplicationContext applicationContext;

    public DataSourceCreator() {
        checkDataSource();
    }

    private void checkDataSource() {
        try {
            Class.forName(DRUID_DATASOURCE);
            if (LogUtil.LOG.isInfoEnabled())
                LogUtil.LOG.info("<===================动态数据源-检测到【druid】存在，使用druid数据源==============>");
            druidExists = true;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName(HIKARI_DATASOURCE);
                if (LogUtil.LOG.isInfoEnabled())
                    LogUtil.LOG.info("动态数据源-,如配置中使用hikari数据源");
                hikariExists = true;
            } catch (ClassNotFoundException e1) {
                //TODO 其他数据源
            }
        }
    }

    /**
     * 创建数据源
     *
     * @param dataSourceProperty 数据源属性配置
     * @param config             数据池配置
     * @return DataSource
     */
    public DataSource createDataSource(DataSourceProperty dataSourceProperty, DruidConfig config) throws ClassNotFoundException {
        //如果是jndi数据源
        String jndiName = dataSourceProperty.getJndiName();
        if (jndiName != null && !jndiName.isEmpty()) {
            // return createJNDIDataSource(jndiName);
        }
        Class<? extends DataSource> type = dataSourceProperty.getType();
        if (type == null) {
            if (druidExists) {
                return createDruidDataSource(dataSourceProperty, config);
            } else if (hikariExists) {
                //  return createHikariDataSource(dataSourceProperty);
            }
        } else if (DRUID_DATASOURCE.equals(type.getName())) {
            return createDruidDataSource(dataSourceProperty, config);
        } else if (HIKARI_DATASOURCE.equals(type.getName())) {
            //  return createHikariDataSource(dataSourceProperty);
        }
        return createBasicDataSource(dataSourceProperty);
    }

    /**
     * 创建基础数据源
     *
     * @param dataSourceProperty 数据源参数
     * @return DataSource
     */
    private DataSource createBasicDataSource(DataSourceProperty dataSourceProperty) {
        try {
            DataSourceBuilder builder = DataSourceBuilder.create();
            builder.driverClassName(dataSourceProperty.getDriverClassName());
            builder.password(dataSourceProperty.getPassword());
            builder.url(dataSourceProperty.getUrl());
            builder.username(dataSourceProperty.getUserName());
            builder.type(dataSourceProperty.getType());
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException("基本数据源创建失败!");
        }
    }

    /**
     * 创建DRUID数据源
     *
     * @param dataSourceProperty 数据源参数
     * @param config
     * @return DataSource
     */
    private DataSource createDruidDataSource(DataSourceProperty dataSourceProperty, DruidConfig config) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(dataSourceProperty.getUserName());
        dataSource.setPassword(dataSourceProperty.getPassword());
        dataSource.setUrl(dataSourceProperty.getUrl());
        dataSource.setDriverClassName(dataSourceProperty.getDriverClassName());
        //  dataSource.setName(dataSourcePro);
        Properties properties = config.toProperties(druidGlobalConfig);
        String filters = properties.getProperty("druid.filters");
        List<Filter> proxyFilters = new ArrayList<>(2);
        if (!StringUtils.isEmpty(filters) && filters.contains("stat")) {
            StatFilter statFilter = new StatFilter();
            statFilter.configFromProperties(properties);
            proxyFilters.add(statFilter);
        }
        if (!StringUtils.isEmpty(filters) && filters.contains("wall")) {
            WallConfig wallConfig = DruidWallConfigUtil.toWallConfig(config.getWall(), druidGlobalConfig.getWall());
            WallFilter wallFilter = new WallFilter();
            wallFilter.setConfig(wallConfig);
            proxyFilters.add(wallFilter);
        }

        if (this.applicationContext != null) {
            for (String filterId : this.druidGlobalConfig.getProxyFilters()) {
                proxyFilters.add(this.applicationContext.getBean(filterId, Filter.class));
            }
        }
        dataSource.setProxyFilters(proxyFilters);
        dataSource.configFromPropety(properties);
        //连接参数单独设置
        dataSource.setConnectProperties(config.getConnectionProperties());
        //设置druid内置properties不支持的的参数
        Boolean testOnReturn = config.getTestOnReturn() == null ? druidGlobalConfig.getTestOnReturn() : config.getTestOnReturn();
        if (testOnReturn != null && testOnReturn.equals(true)) {
            dataSource.setTestOnReturn(true);
        }
        int validationQueryTimeout = config.getValidationQueryTimeout() <= -1 ? druidGlobalConfig.getValidationQueryTimeout() : config.getValidationQueryTimeout();
        if (validationQueryTimeout > -1) {
            dataSource.setValidationQueryTimeout(validationQueryTimeout);
        }

        Boolean sharePreparedStatements = config.getSharePreparedStatements() == null ? druidGlobalConfig.getSharePreparedStatements() : config.getSharePreparedStatements();
        if (sharePreparedStatements != null && sharePreparedStatements.equals(true)) {
            dataSource.setSharePreparedStatements(true);
        }
        int connectionErrorRetryAttempts = config.getConnectionErrorRetryAttempts() == 0 ? druidGlobalConfig.getConnectionErrorRetryAttempts() : config.getConnectionErrorRetryAttempts();
        if (connectionErrorRetryAttempts != 1) {
            dataSource.setConnectionErrorRetryAttempts(connectionErrorRetryAttempts);
        }
        Boolean breakAfterAcquireFailure = config.getBreakAfterAcquireFailure() == null ? druidGlobalConfig.getBreakAfterAcquireFailure() : config.getBreakAfterAcquireFailure();
        if (breakAfterAcquireFailure != null && breakAfterAcquireFailure.equals(true)) {
            dataSource.setBreakAfterAcquireFailure(true);
        }
        try {
            dataSource.init();
        } catch (SQLException e) {
            LogUtil.LOG.error("druid数据源启动失败", e);
        }
        return dataSource;
    }
}
