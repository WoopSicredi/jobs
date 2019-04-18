package com.vollino.poll.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * @author Bruno Vollino
 */
@ControllerAdvice
public class ConstraintViolationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseBody> handleException(ConstraintViolationException ex) {
        ErrorResponseBody body = new ErrorResponseBody();

        ex.getConstraintViolations().stream()
                .forEach(error -> body.withError(error.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(body);
    }
}
