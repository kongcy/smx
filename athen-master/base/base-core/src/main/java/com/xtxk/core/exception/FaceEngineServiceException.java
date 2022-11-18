package com.xtxk.core.exception;

/**
 * @author chenying
 * @date 2022/11/8 2:51 PM
 * @time 2:51 PM
 * @since 1.0.0
 **/
public class FaceEngineServiceException  extends RuntimeException{
    public FaceEngineServiceException() {
        super();
    }

    public FaceEngineServiceException(String message) {
        super(message);
    }

    public FaceEngineServiceException(Throwable throwable) {
        super(throwable);
    }

    public FaceEngineServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
