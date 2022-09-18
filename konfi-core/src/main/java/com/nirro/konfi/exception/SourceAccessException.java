package com.nirro.konfi.exception;

public class SourceAccessException extends RuntimeException {
    public SourceAccessException() {
    }

    public SourceAccessException(String message) {
        super(message);
    }

    public SourceAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceAccessException(Throwable cause) {
        super(cause);
    }

    public SourceAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}