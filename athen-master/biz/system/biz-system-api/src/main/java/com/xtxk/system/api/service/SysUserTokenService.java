package com.xtxk.system.api.service;

import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.system.api.model.SysUserState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 用户token
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/4/21
 */
@NoTokenVerify
@FeignClient(name = "biz-system-service")
public interface SysUserTokenService {
    /**
     * 保存用户token
     */
    @RequestMapping(value = "/system/saveUserToken", method = RequestMethod.POST)
    boolean saveUserToken(@RequestBody SysUserState userState);

    /**
     * 删除用户token
     */
    @RequestMapping(value = "/system/deleteUserToken", method = RequestMethod.POST)
    boolean deleteUserToken(@RequestParam String userId);
}
