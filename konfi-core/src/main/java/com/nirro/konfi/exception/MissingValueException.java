package com.nirro.konfi.exception;

/**
 * A {@code MissingValueException} is thrown when value
 * is missing
 */
public class MissingValueException extends RuntimeException {
    /**
     * Creates a new missing value exception
     */
    public MissingValueException() {
    }

    /**
     * Creates a new missing value exception
     * @param message detailed message
     */
    public MissingValueException(String message) {
        super(message);
    }

    /**
     * Creates a new missing value exception
     * @param message detailed message
     * @param cause cause
     */
    public MissingValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new missing value exception
     * @param cause cause
     */
    public MissingValueException(Throwable cause) {
        super(cause);
    }

}