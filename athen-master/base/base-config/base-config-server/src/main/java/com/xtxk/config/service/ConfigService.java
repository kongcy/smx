package com.xtxk.config.service;


import com.xtxk.config.model.CfgTable;

import java.util.List;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 9:46
 * since: 1.0.0
 */
public interface ConfigService {

    /**
     * 通过application来查询所有配置
     *
     * @param application 应用名 默认config-server
     * @return ConfigTable
     **/
    default List<CfgTable> findByApplication(String application){
        return null;
    }

    /**
     * 获取不同环境的配置
     *
     * @param application 应用
     * @param profile 对应环境 dev/test/pro
     **/
    default List<CfgTable> findByApplicationAndProfile(String application, String profile){
        return null;
    }

    /**
     * 获取不同环境下不同标签的配置
     *
     * @param application
     * @param profile
     * @param label
     **/
    default List<CfgTable> findByCondition(String application, String profile, String label){
        return null;
    }


    default List<CfgTable> selectByExampleExt(String application, String profile, String label){
        return null;
    }

    default List<Map<String,String>> findTables(){
        return null;
    }
}
