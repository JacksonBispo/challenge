package com.challeng.exception;

public class CpfinvalidException extends RuntimeException {

    public CpfinvalidException(String message) {
        super(message);
    }

    public CpfinvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CpfinvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
