package com.xtxk.dataSource.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig implements Serializable {
    /**
     * 必须设置默认的库,默认master
     */
    private String primary = "master";
    private String application;
    private Map<String, DataSourceProperty> group = new LinkedHashMap<>();

    @NestedConfigurationProperty
    private DruidConfig druid = new DruidConfig();

    public DataSourceConfig() {
    }

    public DataSourceConfig(String application) {
        this.application = application;
    }
}
