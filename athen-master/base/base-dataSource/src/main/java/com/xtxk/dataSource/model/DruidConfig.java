/**
 * Copyright © 2018 organization baomidou
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package com.xtxk.dataSource.model;

import com.xtxk.core.util.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.alibaba.druid.pool.DruidAbstractDataSource.*;


/**
 * Druid参数配置
 *
 * @author TaoYu
 * @since 1.2.0
 */
public class DruidConfig {

    private int initialSize;
    private int maxActive;
    private int minIdle;
    private int maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long timeBetweenLogStatsMillis;
    private int statSqlMaxSize;
    private long minEvictableIdleTimeMillis;
    private long maxEvictableIdleTimeMillis;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private String validationQuery;
    private int validationQueryTimeout;
    private boolean useGlobalDataSourceStat;
    private boolean asyncInit;
    private String filters;
    private boolean clearFiltersEnable;
    private boolean resetStatEnable;
    private int notFullTimeoutRetryCount;
    private int maxWaitThreadCount;
    private boolean failFast;
    private long phyTimeoutMillis;
    private boolean keepAlive;
    private boolean poolPreparedStatements;
    private boolean initVariants;
    private boolean initGlobalVariants;
    private boolean useUnfairLock;
    private boolean killWhenSocketReadTimeout;
    private Properties connectionProperties;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String initConnectionSqls;
    private boolean sharePreparedStatements;
    private int connectionErrorRetryAttempts;
    private boolean breakAfterAcquireFailure;

    private String publicKey;

    @NestedConfigurationProperty
    private DruidWallConfig wall = new DruidWallConfig();

    @NestedConfigurationProperty
    private DruidStatConfig stat = new DruidStatConfig();

    private List<String> proxyFilters = new ArrayList<>(2);

    public Properties toProperties(DruidConfig globalConfig) {
        Properties properties = new Properties();
        int tempInitialSize = globalConfig.getInitialSize();
        if (tempInitialSize != DEFAULT_INITIAL_SIZE) {
            properties.setProperty("druid.initialSize", String.valueOf(tempInitialSize));
        }

        int tempMaxActive = globalConfig.getMaxActive();
        if (tempMaxActive != (DEFAULT_MAX_WAIT)) {
            properties.setProperty("druid.maxActive", String.valueOf(tempMaxActive));
        }

        int tempMinIdle = globalConfig.getMinIdle();
        if (tempMinIdle != (DEFAULT_MIN_IDLE)) {
            properties.setProperty("druid.minIdle", String.valueOf(tempMinIdle));
        }

        int tempMaxWait = globalConfig.getMaxWait();
        if (tempMaxWait != (DEFAULT_MAX_WAIT)) {
            properties.setProperty("druid.maxWait", String.valueOf(tempMaxWait));
        }

        long tempTimeBetweenEvictionRunsMillis = globalConfig.getTimeBetweenEvictionRunsMillis();
        if (tempTimeBetweenEvictionRunsMillis != (DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS)) {
            properties.setProperty("druid.timeBetweenEvictionRunsMillis", String.valueOf(tempTimeBetweenEvictionRunsMillis));
        }

        long tempTimeBetweenLogStatsMillis = globalConfig.getTimeBetweenLogStatsMillis();
        if (tempTimeBetweenLogStatsMillis > 0) {
            properties.setProperty("druid.timeBetweenLogStatsMillis", String.valueOf(tempTimeBetweenLogStatsMillis));
        }

        int tempStatSqlMaxSize = globalConfig.getStatSqlMaxSize();
        if (tempStatSqlMaxSize != 0) {
            properties.setProperty("druid.stat.sql.MaxSize", String.valueOf(tempStatSqlMaxSize));
        }

        long tempMinEvictableIdleTimeMillis = globalConfig.getMinEvictableIdleTimeMillis();
        if (tempMinEvictableIdleTimeMillis != (DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS)) {
            properties.setProperty("druid.minEvictableIdleTimeMillis", String.valueOf(tempMinEvictableIdleTimeMillis));
        }

        long tempMaxEvictableIdleTimeMillis = globalConfig.getMaxEvictableIdleTimeMillis();
        if (tempMaxEvictableIdleTimeMillis != (DEFAULT_MAX_EVICTABLE_IDLE_TIME_MILLIS)) {
            properties.setProperty("druid.maxEvictableIdleTimeMillis", String.valueOf(tempMaxEvictableIdleTimeMillis));
        }

        boolean tempTestWhileIdle = globalConfig.getTestWhileIdle();
        if (tempTestWhileIdle != (DEFAULT_WHILE_IDLE)) {
            properties.setProperty("druid.testWhileIdle", "false");
        }

        boolean tempTestOnBorrow = globalConfig.getTestOnBorrow();
        if (tempTestOnBorrow != (DEFAULT_TEST_ON_BORROW)) {
            properties.setProperty("druid.testOnBorrow", "true");
        }

        String tempValidationQuery = validationQuery == null ? globalConfig.getValidationQuery() : validationQuery;
        if (tempValidationQuery != null && tempValidationQuery.length() > 0) {
            properties.setProperty("druid.validationQuery", tempValidationQuery);
        }

        boolean tempUseGlobalDataSourceStat = globalConfig.getUseGlobalDataSourceStat();
        if (tempUseGlobalDataSourceStat == Boolean.TRUE) {
            properties.setProperty("druid.useGlobalDataSourceStat", "true");
        }

        boolean tempAsyncInit = globalConfig.getAsyncInit();
        if (tempAsyncInit == Boolean.TRUE) {
            properties.setProperty("druid.asyncInit", "true");
        }

        //filters单独处理，默认了stat,wall
        String tempFilters = filters == null ? globalConfig.getFilters() : filters;
        if (tempFilters == null) {
            tempFilters = "stat";
        }
        if (publicKey != null && publicKey.length() > 0 && !tempFilters.contains("config")) {
            tempFilters += ",config";
        }
        properties.setProperty("druid.filters", tempFilters);

        boolean tempClearFiltersEnable = globalConfig.getClearFiltersEnable();
        if (tempClearFiltersEnable == Boolean.FALSE) {
            properties.setProperty("druid.clearFiltersEnable", "false");
        }

        boolean tempResetStatEnable = globalConfig.getResetStatEnable();
        if (tempResetStatEnable == (Boolean.FALSE)) {
            properties.setProperty("druid.resetStatEnable", "false");
        }

        int tempNotFullTimeoutRetryCount = globalConfig.getNotFullTimeoutRetryCount();
        if (tempNotFullTimeoutRetryCount != 0) {
            properties.setProperty("druid.notFullTimeoutRetryCount", String.valueOf(tempNotFullTimeoutRetryCount));
        }

        int tempMaxWaitThreadCount = globalConfig.getMaxWaitThreadCount();
        if (tempMaxWaitThreadCount != -1) {
            properties.setProperty("druid.maxWaitThreadCount", String.valueOf(tempMaxWaitThreadCount));
        }

        boolean tempFailFast = globalConfig.getFailFast();
        if (tempFailFast == (Boolean.TRUE)) {
            properties.setProperty("druid.failFast", "true");
        }

        long tempPhyTimeoutMillis = globalConfig.getPhyTimeoutMillis();
        if (tempPhyTimeoutMillis != DEFAULT_PHY_TIMEOUT_MILLIS) {
            properties.setProperty("druid.phyTimeoutMillis", String.valueOf(tempPhyTimeoutMillis));
        }

        boolean tempKeepAlive = globalConfig.getKeepAlive();
        if (tempKeepAlive == (Boolean.TRUE)) {
            properties.setProperty("druid.keepAlive", "true");
        }

        boolean tempPoolPreparedStatements = globalConfig.getPoolPreparedStatements();
        if (tempPoolPreparedStatements == (Boolean.TRUE)) {
            properties.setProperty("druid.poolPreparedStatements", "true");
        }

        boolean tempInitVariants = globalConfig.getInitVariants();
        if (tempInitVariants == (Boolean.TRUE)) {
            properties.setProperty("druid.initVariants", "true");
        }

        boolean tempInitGlobalVariants = globalConfig.getInitGlobalVariants();
        if (tempInitGlobalVariants == (Boolean.TRUE)) {
            properties.setProperty("druid.initGlobalVariants", "true");
        }

        boolean tempUseUnfairLock = globalConfig.getUseUnfairLock();
        properties.setProperty("druid.useUnfairLock", String.valueOf(tempUseUnfairLock));

        boolean tempKillWhenSocketReadTimeout = globalConfig.getKillWhenSocketReadTimeout();
        if (tempKillWhenSocketReadTimeout == (Boolean.TRUE)) {
            properties.setProperty("druid.killWhenSocketReadTimeout", "true");
        }

        Properties tempConnectProperties = connectionProperties == null ? globalConfig.getConnectionProperties() : connectionProperties;

        if (publicKey != null && publicKey.length() > 0) {
            if (tempConnectProperties == null) {
                tempConnectProperties = new Properties();
            }
            LogUtil.ROOT_LOG.info("动态数据源-检测到您配置了druid加密,加密所需连接参数已为您自动配置");
            tempConnectProperties.setProperty("config.decrypt", "true");
            tempConnectProperties.setProperty("config.decrypt.key", publicKey);
        }
        connectionProperties = tempConnectProperties;

        int tempMaxPoolPreparedStatementPerConnectionSize = globalConfig.getMaxPoolPreparedStatementPerConnectionSize();
        if (tempMaxPoolPreparedStatementPerConnectionSize != 10) {
            properties.setProperty("druid.maxPoolPreparedStatementPerConnectionSize", String.valueOf(tempMaxPoolPreparedStatementPerConnectionSize));
        }

        String tempInitConnectionSqls = initConnectionSqls == null ? globalConfig.getInitConnectionSqls() : initConnectionSqls;
        if (tempInitConnectionSqls != null && tempInitConnectionSqls.length() > 0) {
            properties.setProperty("druid.initConnectionSqls", tempInitConnectionSqls);
        }
        //stat配置参数
        Boolean tempLogSlowSql = stat.getLogSlowSql() == null ? globalConfig.stat.getLogSlowSql() : stat.getLogSlowSql();
        if (tempLogSlowSql != null && tempLogSlowSql) {
            properties.setProperty("druid.stat.logSlowSql", "true");
        }
        Long tempSlowSqlMillis = stat.getSlowSqlMillis() == null ? globalConfig.stat.getSlowSqlMillis() : stat.getSlowSqlMillis();
        if (tempSlowSqlMillis != null) {
            properties.setProperty("druid.stat.slowSqlMillis", tempSlowSqlMillis.toString());
        }

        Boolean tempMergeSql = stat.getMergeSql() == null ? globalConfig.stat.getMergeSql() : stat.getMergeSql();
        if (tempMergeSql != null && tempMergeSql) {
            properties.setProperty("druid.stat.mergeSql", "true");
        }
        return properties;
    }

    public List<String> getProxyFilters() {
        return proxyFilters;
    }

    public void setProxyFilters(List<String> proxyFilters) {
        this.proxyFilters = proxyFilters;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Long getTimeBetweenLogStatsMillis() {
        return timeBetweenLogStatsMillis;
    }

    public void setTimeBetweenLogStatsMillis(Long timeBetweenLogStatsMillis) {
        this.timeBetweenLogStatsMillis = timeBetweenLogStatsMillis;
    }

    public int getStatSqlMaxSize() {
        return statSqlMaxSize;
    }

    public void setStatSqlMaxSize(int statSqlMaxSize) {
        this.statSqlMaxSize = statSqlMaxSize;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public Long getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }

    public void setMaxEvictableIdleTimeMillis(Long maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public int getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(int validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public Boolean getUseGlobalDataSourceStat() {
        return useGlobalDataSourceStat;
    }

    public void setUseGlobalDataSourceStat(Boolean useGlobalDataSourceStat) {
        this.useGlobalDataSourceStat = useGlobalDataSourceStat;
    }

    public Boolean getAsyncInit() {
        return asyncInit;
    }

    public void setAsyncInit(Boolean asyncInit) {
        this.asyncInit = asyncInit;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Boolean getClearFiltersEnable() {
        return clearFiltersEnable;
    }

    public void setClearFiltersEnable(Boolean clearFiltersEnable) {
        this.clearFiltersEnable = clearFiltersEnable;
    }

    public Boolean getResetStatEnable() {
        return resetStatEnable;
    }

    public void setResetStatEnable(Boolean resetStatEnable) {
        this.resetStatEnable = resetStatEnable;
    }

    public int getNotFullTimeoutRetryCount() {
        return notFullTimeoutRetryCount;
    }

    public void setNotFullTimeoutRetryCount(int notFullTimeoutRetryCount) {
        this.notFullTimeoutRetryCount = notFullTimeoutRetryCount;
    }

    public int getMaxWaitThreadCount() {
        return maxWaitThreadCount;
    }

    public void setMaxWaitThreadCount(int maxWaitThreadCount) {
        this.maxWaitThreadCount = maxWaitThreadCount;
    }

    public Boolean getFailFast() {
        return failFast;
    }

    public void setFailFast(Boolean failFast) {
        this.failFast = failFast;
    }

    public Long getPhyTimeoutMillis() {
        return phyTimeoutMillis;
    }

    public void setPhyTimeoutMillis(Long phyTimeoutMillis) {
        this.phyTimeoutMillis = phyTimeoutMillis;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Boolean getInitVariants() {
        return initVariants;
    }

    public void setInitVariants(Boolean initVariants) {
        this.initVariants = initVariants;
    }

    public Boolean getInitGlobalVariants() {
        return initGlobalVariants;
    }

    public void setInitGlobalVariants(Boolean initGlobalVariants) {
        this.initGlobalVariants = initGlobalVariants;
    }

    public Boolean getUseUnfairLock() {
        return useUnfairLock;
    }

    public void setUseUnfairLock(Boolean useUnfairLock) {
        this.useUnfairLock = useUnfairLock;
    }

    public Boolean getKillWhenSocketReadTimeout() {
        return killWhenSocketReadTimeout;
    }

    public void setKillWhenSocketReadTimeout(Boolean killWhenSocketReadTimeout) {
        this.killWhenSocketReadTimeout = killWhenSocketReadTimeout;
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getInitConnectionSqls() {
        return initConnectionSqls;
    }

    public void setInitConnectionSqls(String initConnectionSqls) {
        this.initConnectionSqls = initConnectionSqls;
    }

    public Boolean getSharePreparedStatements() {
        return sharePreparedStatements;
    }

    public void setSharePreparedStatements(Boolean sharePreparedStatements) {
        this.sharePreparedStatements = sharePreparedStatements;
    }

    public int getConnectionErrorRetryAttempts() {
        return connectionErrorRetryAttempts;
    }

    public void setConnectionErrorRetryAttempts(int connectionErrorRetryAttempts) {
        this.connectionErrorRetryAttempts = connectionErrorRetryAttempts;
    }

    public Boolean getBreakAfterAcquireFailure() {
        return breakAfterAcquireFailure;
    }

    public void setBreakAfterAcquireFailure(Boolean breakAfterAcquireFailure) {
        this.breakAfterAcquireFailure = breakAfterAcquireFailure;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public DruidWallConfig getWall() {
        return wall;
    }

    public void setWall(DruidWallConfig wall) {
        this.wall = wall;
    }

    public DruidStatConfig getStat() {
        return stat;
    }

    public void setStat(DruidStatConfig stat) {
        this.stat = stat;
    }
}