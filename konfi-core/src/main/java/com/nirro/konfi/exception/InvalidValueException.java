package com.nirro.konfi.exception;

/**
 * A {@code InvalidValueException} is thrown when value
 * doesn't match the property definition
 */
public class InvalidValueException extends RuntimeException {

    /**
     * Creates a new invalid value exception
     */
    public InvalidValueException() {
    }

    /**
     * Creates a new invalid value exception
     * @param message detailed message
     */
    public InvalidValueException(String message) {
        super(message);
    }

    /**
     * Creates a new invalid value exception
     * @param message detailed message
     * @param cause cause
     */
    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new invalid value exception
     * @param cause cause
     */
    public InvalidValueException(Throwable cause) {
        super(cause);
    }

}
