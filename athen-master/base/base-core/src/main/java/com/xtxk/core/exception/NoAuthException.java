package com.xtxk.core.exception;

/**
 * @Description 没有token认证异常
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/10
 */
public class NoAuthException extends RuntimeException{
    public NoAuthException() {
        super();
    }

    public NoAuthException(String message) {
        super(message);
    }

    public NoAuthException(Throwable throwable) {
        super(throwable);
    }

    public NoAuthException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
