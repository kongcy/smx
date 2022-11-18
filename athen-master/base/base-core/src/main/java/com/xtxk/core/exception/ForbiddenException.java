package com.xtxk.core.exception;

/**
 * @Description 接口操作权限
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/28
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable throwable) {
        super(throwable);
    }

    public ForbiddenException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
