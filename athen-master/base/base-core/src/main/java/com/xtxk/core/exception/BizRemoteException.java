package com.xtxk.core.exception;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/11/1
 */
public class BizRemoteException extends RuntimeException{
    public BizRemoteException() {
        super();
    }

    public BizRemoteException(String message) {
        super(message);
    }

    public BizRemoteException(Throwable throwable) {
        super(throwable);
    }

    public BizRemoteException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
