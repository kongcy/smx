package com.xtxk.system.api.service;

import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.core.vo.PageList;
import com.xtxk.system.api.model.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 系统登录日志类
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/17
 */
@NoTokenVerify
@FeignClient(name = "biz-system-service")
public interface SysLogService {
    /**
     * 保存日志
     */
    @RequestMapping(value = "/system/saveLog", method = RequestMethod.POST)
    boolean saveLog(@RequestBody SysLog sysLog);

    @RequestMapping(value = "/system/saveLog", method = RequestMethod.GET)
    boolean saveLog(@RequestParam String userId, @RequestParam String ip);

    @RequestMapping(value = "/system/findLogs", method = RequestMethod.POST)
    PageList findLogs(@RequestBody SysLog sysLog);
}
