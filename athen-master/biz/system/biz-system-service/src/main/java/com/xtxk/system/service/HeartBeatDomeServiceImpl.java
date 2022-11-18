package com.xtxk.system.service;

import com.xtxk.system.api.service.HeartBeatDomeService;
import org.springframework.stereotype.Component;

/**
 * Created by foresee on 2019-01-27.
 */
@Component
public class HeartBeatDomeServiceImpl implements HeartBeatDomeService {
    @Override
    public String invoke() {
        return "Dubbo System service start !!!!";
    }
}
