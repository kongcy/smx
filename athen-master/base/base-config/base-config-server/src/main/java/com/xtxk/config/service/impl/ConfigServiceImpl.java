package com.xtxk.config.service.impl;

import com.xtxk.config.model.CfgTable;
import com.xtxk.config.model.CfgTableExample;
import com.xtxk.config.repository.CfgTableMapper;
import com.xtxk.config.service.ConfigService;
import com.xtxk.core.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 9:49
 * since: 1.0.0
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private CfgTableMapper mapper;

    @Override
    public List<CfgTable> selectByExampleExt(String application, String profile, String module) {
        return mapper.selectByApplicationAndModule(application, module, null);
    }

    @Override
    public List<Map<String, String>> findTables() {
        return mapper.findTables();
    }

    @Override
    public List<CfgTable> findByApplication(String application) {
        CfgTableExample example = new CfgTableExample();
        CfgTableExample.Criteria criteria = example.createCriteria();
        if (U.isNotBlank(application)) {
            criteria.andApplicationEqualTo(application);
            return mapper.selectByExample(example);
        }
        return mapper.selectByExample(null);
    }

    @Override
    public List<CfgTable> findByApplicationAndProfile(String application, String profile) {
        List<CfgTable> configTables;
        if (U.isBlank(profile)) {
            return findByApplication(application);
        } else {
            configTables = findByApplication(null);
            //暂时没有使用
//            List<Map<String,String>> dataList = findTables();
//            String tableName = "";
//            if(dataList!=null){
//                for(Map<String,String> map:dataList){
//                    for(Map.Entry<String,String> entry:map.entrySet()){
//                        if(entry.getValue() instanceof String&&entry.getValue().contains(profile)){
//                            tableName = entry.getValue();
//                            break;
//                        }
//                    }
//                }
//            }
//            if(U.isNotBlank(tableName)){
//                configTables=selectByExampleExt(application,tableName,null);
//            }else{
//                configTables = findByApplication(null);
//            }
        }
        return configTables;
    }


}
