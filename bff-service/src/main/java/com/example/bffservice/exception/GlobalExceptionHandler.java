package com.example.bffservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "timestamp", Instant.now().toString(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", "User not found",
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(DownstreamServiceException.class)
    public ResponseEntity<?> handleDownstreamError(DownstreamServiceException ex) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        return ResponseEntity.status(status)
                .body(Map.of(
                        "timestamp", Instant.now().toString(),
                        "status", status.value(),
                        "error", "Downstream service error",
                        "service", ex.getServiceName(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(Map.of(
                        "timestamp", Instant.now().toString(),
                        "status", status.value(),
                        "error", "Internal server error",
                        "message", ex.getMessage()
                ));
    }
}
