package com.xtxk.system.api.service;

import com.xtxk.core.annotation.NoTokenVerify;

/**
 * @author chenying
 * @date 2022/11/14 9:56 AM
 * @time 9:56 AM
 * @since 1.0.0
 **/
@NoTokenVerify
public interface SysFaceService {
    /**
     * 更新用户头像
     */
    int updateUserImage();

    int updateUserInfo();
}
