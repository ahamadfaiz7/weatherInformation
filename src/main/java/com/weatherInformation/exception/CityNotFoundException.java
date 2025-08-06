package com.weatherInformation.exception;

/**
 * Custom exception thrown when a city is not found in both in-memory and external API.
 */
public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String message) {
        super(message);
    }
}