package com.xtxk.system.service;

import com.xtxk.system.api.model.SysUserState;
import com.xtxk.system.api.service.SysUserTokenService;
import com.xtxk.system.repository.SysUserStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/4/21
 */
@RestController
public class UserTokenServiceImpl implements SysUserTokenService {
    @Autowired
    private SysUserStateMapper userStateMapper;
    /**
     * 保存用户token
     *
     * @param userState
     */
    @Override
    public boolean saveUserToken(@RequestBody SysUserState userState) {
        return false;
    }

    /**
     * 删除用户token
     *
     * @param userId
     */
    @Override
    public boolean deleteUserToken(@RequestParam String userId) {
        return false;
    }
}
