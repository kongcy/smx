package com.xtxk.core.exception;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/1/13
 */
public class PluginDecodeErrorException extends RuntimeException {
    public PluginDecodeErrorException() {
        super();
    }

    public PluginDecodeErrorException(String message) {
        super(message);
    }

    public PluginDecodeErrorException(Throwable throwable) {
        super(throwable);
    }

    public PluginDecodeErrorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
