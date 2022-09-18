package com.nirro.konfi.exception;

public class MissingValueException extends RuntimeException {
    public MissingValueException() {
    }

    public MissingValueException(String message) {
        super(message);
    }

    public MissingValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingValueException(Throwable cause) {
        super(cause);
    }

    public MissingValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}