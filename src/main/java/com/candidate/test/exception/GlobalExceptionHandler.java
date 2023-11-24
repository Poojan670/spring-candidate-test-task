package com.candidate.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Asia/Kathmandu"));

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<Object> handleBadRequest(CustomException ex) {
        logger.info(ex.getClass().getName());

        final List<String> errors = new ArrayList<String>();
        errors.add("Errors Occurred");

        final ApiError apiError = new ApiError(
                ex.getLocalizedMessage(),
                badRequest,
                timestamp,
                errors
        );
        return new ResponseEntity<>(apiError, badRequest);
    }

}
