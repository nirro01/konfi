package com.nirro.konfi.exception;

/**
 * A {@code MissingKonfiPropertyAnnotationException} is thrown when
 * calling a method that doesn't have KonfiProperty annotation
 */
public class MissingKonfiPropertyAnnotationException extends RuntimeException {

    /**
     * Creates a new missing konfi property annotation exception
     */
    public MissingKonfiPropertyAnnotationException() {
    }

    /**
     * Creates a new missing konfi property annotation exception
     * @param message detailed message
     */
    public MissingKonfiPropertyAnnotationException(String message) {
        super(message);
    }

    /**
     * Creates a new missing konfi property annotation exception
     * @param message detailed message
     * @param cause cause
     */
    public MissingKonfiPropertyAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new missing konfi property annotation exception
     * @param cause cause
     */
    public MissingKonfiPropertyAnnotationException(Throwable cause) {
        super(cause);
    }

}