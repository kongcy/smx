package com.xtxk.system.api.service;

import com.xtxk.core.annotation.NoTokenVerify;

/**
 * Created by foresee on 2019-01-27.
 * dubbo 心跳测试服务
 */
@NoTokenVerify
public interface HeartBeatDomeService {
    public String invoke();
}
