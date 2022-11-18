package com.xtxk.system.api.service;

import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.core.vo.PageList;
import com.xtxk.system.api.model.SysLoginRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 登录记录表(登录锁定, 如果5分钟内登录次数大于3次 ， 将锁定10分钟)
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/25
 */
@NoTokenVerify
@FeignClient(name = "biz-system-service")
public interface SysLoginRecordService {

    @RequestMapping(value = "/system/finsPageRecordByCondition", method = RequestMethod.POST)
    PageList findPageRecordByCondition(@RequestBody SysLoginRecord record);

    /**
     * 保存记录
     */
    @RequestMapping(value = "/system/saveRecord", method = RequestMethod.POST)
    boolean saveRecord(@RequestBody SysLoginRecord record);

}
