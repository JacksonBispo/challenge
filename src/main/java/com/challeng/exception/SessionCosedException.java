package com.challeng.exception;

public class SessionCosedException extends RuntimeException {

    public SessionCosedException(String message) {
        super(message);
    }

    public SessionCosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionCosedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
