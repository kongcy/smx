package com.xtxk.core.exception;

/**
 * @Description 业务数据库操作异常
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/10/29
 */
public class BizSQLException extends RuntimeException {
    public BizSQLException() {
        super();
    }

    public BizSQLException(String message) {
        super(message);
    }

    public BizSQLException(Throwable throwable) {
        super(throwable);
    }

    public BizSQLException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
