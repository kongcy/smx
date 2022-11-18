package com.xtxk.core.exception;

/**
 * @Description 定时任务异常
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/10/26
 */
public class TaskIllegalArgumentException extends RuntimeException {
    public TaskIllegalArgumentException() {
        super();
    }

    public TaskIllegalArgumentException(String message) {
        super(message);
    }

    public TaskIllegalArgumentException(Throwable throwable) {
        super(throwable);
    }

    public TaskIllegalArgumentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
