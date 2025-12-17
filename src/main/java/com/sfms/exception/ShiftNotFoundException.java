package com.sfms.exception;

/**
 * Custom exception for when a shift is not found.
 */
public class ShiftNotFoundException extends Exception {
    public ShiftNotFoundException(String message) {
        super(message);
    }

    public ShiftNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
