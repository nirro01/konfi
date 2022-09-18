package com.nirro.konfi.exception;

/**
 * A {@code InvalidReturnTypeException} is thrown when the return type
 * of the requested method is not valid
 */
public class InvalidReturnTypeException extends RuntimeException {

    /**
     * Creates a new invalid return type exception
     */
    public InvalidReturnTypeException() {
    }

    /**
     * Creates a new invalid return type exception
     * @param message detailed message
     */
    public InvalidReturnTypeException(String message) {
        super(message);
    }

    /**
     * Creates a new invalid return type exception
     * @param message detailed message
     * @param cause cause
     */
    public InvalidReturnTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new invalid return type exception
     * @param cause cause
     */
    public InvalidReturnTypeException(Throwable cause) {
        super(cause);
    }
}
