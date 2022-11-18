package com.xtxk.core.exception;

import feign.FeignException;

/**
 * @Description 认证错误异常
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/26
 */
public class AuthenticationErrorException extends FeignException {
    private int status;

    public AuthenticationErrorException(int status, String message) {
        super(status,message);
        this.status = status;
    }
}
