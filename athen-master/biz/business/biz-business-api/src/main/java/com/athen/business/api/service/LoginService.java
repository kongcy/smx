package com.athen.business.api.service;

import com.athen.business.api.model.SysUser;

/**
 * 登陆
 *
 * @author chenying
 * @date 2022/10/17 1:16 PM
 * @time 1:16 PM
 * @since 1.0.0
 **/
public interface LoginService {
    /**
     * 登陆接口
     *
     * @param username 用户名
     * @param password 密码
     */
    SysUser login(String username, String password);

}
