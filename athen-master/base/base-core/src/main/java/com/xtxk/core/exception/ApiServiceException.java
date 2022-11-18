package com.xtxk.core.exception;

/**
 * 工具柜异常类
 * @author chenying
 * @date 2022/11/4 8:42 AM
 * @time 8:42 AM
 * @since 1.0.0
 **/
public class ApiServiceException extends RuntimeException {
    public ApiServiceException() {
        super();
    }

    public ApiServiceException(String message) {
        super(message);
    }

    public ApiServiceException(Throwable throwable) {
        super(throwable);
    }

    public ApiServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
