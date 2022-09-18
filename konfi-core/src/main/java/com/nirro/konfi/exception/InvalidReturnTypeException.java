package com.nirro.konfi.exception;

public class InvalidReturnTypeException extends RuntimeException {
    public InvalidReturnTypeException() {
    }

    public InvalidReturnTypeException(String message) {
        super(message);
    }

    public InvalidReturnTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReturnTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidReturnTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
