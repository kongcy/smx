package com.xtxk.dataSource.model;

import com.xtxk.config.util.ConfigConstants;
import com.xtxk.config.util.PropertyUtils;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.sql.DataSource;
import java.io.Serializable;

/**
 * @Description 数据源基础属性
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/30
 */
public class DataSourceProperty implements Serializable {
    private String url;
    private String userName;
    private String password;
    private String driverClassName;
    private Class<? extends DataSource> type;
    private String application;
    /**
     * jndi数据源名称(设置即表示启用)
     */
    private String jndiName;

    public DataSourceProperty() {
    }

    public DataSourceProperty(String application) {
        this.application = application;
    }


    public String getUrl() {
        ;
        return PropertyUtils.isConfig() ? PropertyUtils.getStrPropertyValue(application, ConfigConstants.DATASOURCE_URL_KEY) : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return PropertyUtils.isConfig() ? PropertyUtils.getStrPropertyValue(application, ConfigConstants.DATASOURCE_USER_NAME_KEY) : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return PropertyUtils.isConfig() ? PropertyUtils.getStrPropertyValue(application, ConfigConstants.DATASOURCE_PASSWORD_KEY) : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return PropertyUtils.isConfig() ? PropertyUtils.getStrPropertyValue(application, ConfigConstants.DATASOURCE_DRIVER_CLASS_NAME_KEY) : driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Class getType() throws ClassNotFoundException {
        return PropertyUtils.isConfig() ? Class.forName(PropertyUtils.getStrPropertyValue(application, ConfigConstants.DATASOURCE_TYPE)) : type;
    }

    public void setType(String type) {
        try {
            this.type = (Class<? extends DataSource>) Class.forName(type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }
}
