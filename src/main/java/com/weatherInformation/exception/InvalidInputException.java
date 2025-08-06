package com.weatherInformation.exception;

/**
 * Custom exception thrown when input is missing.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}