package com.nirro.konfi.exception;

/**
 * A {@code SourceAccessException} is thrown when unable
 * to receive properties from source
 */
public class SourceAccessException extends RuntimeException {

    /**
     * Creates a new source access exception
     */
    public SourceAccessException() {
    }

    /**
     * Creates a new source access exception
     * @param message detailed message
     */
    public SourceAccessException(String message) {
        super(message);
    }

    /**
     * Creates a new source access exception
     * @param message detailed message
     * @param cause cause
     */
    public SourceAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new source access exception
     * @param cause cause
     */
    public SourceAccessException(Throwable cause) {
        super(cause);
    }

}