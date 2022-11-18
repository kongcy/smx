package com.xtxk.config.model;


import com.xtxk.core.util.A;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 15:12
 * since: 1.0.0
 */
public class Config implements Serializable {
    private String name;
    private List<String> profiles;
    private String version;
    private List<PropertySource> propertySources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<PropertySource> getPropertySources() {
        return propertySources;
    }

    public void setPropertySources(List<PropertySource> propertySources) {
        this.propertySources = propertySources;
    }

    public static class PropertySource{
        private String name;
        private Map<String,Object> source;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, Object> getSource() {
            return source;
        }

        public void setSource(Map<String, Object> source) {
            this.source = source;
        }
    }

    public Map<String,Object> getData(){
        PropertySource source = A.first(this.getPropertySources());
        if(source!=null){
            return source.getSource();
        }
        return null;
    }
}
