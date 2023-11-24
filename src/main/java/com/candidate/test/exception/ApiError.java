package com.candidate.test.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

public class ApiError {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private final List<String> errors;

    public ApiError(String message,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp,
                    List<String> errors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}
