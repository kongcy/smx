package com.xtxk.core.exception;

/**
 * @Description 没有登录
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/21
 */
public class NotLoginException extends RuntimeException {
    public NotLoginException() {
        super();
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(Throwable throwable) {
        super(throwable);
    }

    public NotLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
