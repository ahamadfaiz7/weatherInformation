package com.weatherInformation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
class GlobalExceptionHandler {
    public static final String ERROR = "error";
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        log.error("Generic exception: ", ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(ERROR, "An unexpected error has happened");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCityNotFound(CityNotFoundException ex) {
        log.error("City Not Found exception: ", ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(errorResponse);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, String>> handleInvalidInput(InvalidInputException ex) {
        log.error("Invalid input exception: ", ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
