package com.xtxk.config.service;

import com.xtxk.config.model.CfgTable;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis实现，
 * User: chenying
 * Date: 2019-08-13
 * Time: 17:34
 * since: 1.0.0
 */
@Configuration
@Profile("mybatis")
public class MybatisRepository implements EnvironmentRepository, Ordered {
    @Resource
    private ConfigService service;

    @Override
    public Environment findOne(String application, String profile, String label) {
        LogUtil.ROOT_LOG.info("MybatisRepository--->application：{}, profile: {}, label: {}", application, profile, label);
        if (U.isBlank(application))
            return null;
        List<CfgTable> configTables = service.findByApplicationAndProfile(application, profile);
        if (LogUtil.ROOT_LOG.isInfoEnabled()) {
            LogUtil.ROOT_LOG.info("获取配置属性 configTables：{}", JsonUtil.toJson(configTables));
        }
        Map<String, String> map = new HashMap<>();
        for (CfgTable config : configTables) {
            if (U.isBlank(config.getModule())) {
                map.put(config.getKey(), config.getValue());
            } else if (U.isBlank(config.getApplication()) && U.isNotBlank(config.getModule())) {
                map.put(config.getModule() + "." + config.getKey(), config.getValue());
            } else if (isNotBack(config)) {
                map.put(config.getModule() + "." + config.getKey() + "&" + config.getApplication(), config.getValue());
            }
        }
        Environment environment = new Environment(application);
        environment.add(new PropertySource(application, map));
        return environment;
    }

    /**
     * 判断条件是否为null
     */
    private boolean isNotBack(CfgTable cfgTable) {
        return cfgTable != null && U.isNotBlank(cfgTable.getApplication()) && U.isNotBlank(cfgTable.getModule()) && U.isNotBlank(cfgTable.getKey())&&!"*".equals(cfgTable.getApplication());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
