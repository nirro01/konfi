package com.nirro.konfi.exception;

public class KonfiException extends RuntimeException{
    public KonfiException() {
    }

    public KonfiException(String message) {
        super(message);
    }

    public KonfiException(String message, Throwable cause) {
        super(message, cause);
    }

    public KonfiException(Throwable cause) {
        super(cause);
    }

    public KonfiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
