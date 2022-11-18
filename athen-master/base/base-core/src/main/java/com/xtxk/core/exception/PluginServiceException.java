package com.xtxk.core.exception;

/**
 * @Description 插件异常
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/1/13
 */
public class PluginServiceException extends RuntimeException{
    public PluginServiceException() {
        super();
    }

    public PluginServiceException(String message) {
        super(message);
    }

    public PluginServiceException(Throwable throwable) {
        super(throwable);
    }

    public PluginServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
