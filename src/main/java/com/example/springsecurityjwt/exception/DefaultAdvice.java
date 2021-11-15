package com.example.springsecurityjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Response> handleException(CustomNotFoundException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        Response response = new Response(message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
