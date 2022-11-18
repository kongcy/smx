package com.xtxk.system.api.service;

import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.system.api.DTO.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenying
 * @date 2022/11/3 5:30 PM
 * @time 5:30 PM
 * @since 1.0.0
 **/
@NoTokenVerify
@FeignClient(name = "biz-system-service")
public interface ApiService {
    /**
     * 工具柜上传用户信息
     */
    @PostMapping(value = "/api/lockers/uploadUserInfo")
    int uploadUserInfo(@RequestBody UserInfo userInfo);

    /**
     * 修改用户信息
     */
    @PostMapping(value = "/api/lockers/updateUserInfo")
    int updateUserInfo(@RequestBody UserInfo userInfo);
}
