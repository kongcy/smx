package com.xtxk.core.exception;

import feign.FeignException;

/**
 * @Description feign 业务异常
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/7/22
 */
public class BizFeignException extends FeignException {
    private int status;

    public BizFeignException(int status, String message) {
        super(status,message);
        this.status = status;
    }


}
