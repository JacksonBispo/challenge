package com.challeng.exception;

public class AssociadoJaVotouException extends RuntimeException {

    public AssociadoJaVotouException(String message) {
        super(message);
    }

    public AssociadoJaVotouException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssociadoJaVotouException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
