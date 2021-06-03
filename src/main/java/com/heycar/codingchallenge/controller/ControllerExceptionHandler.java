package com.heycar.codingchallenge.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentException(MethodArgumentNotValidException exception) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity handleParserException3(JsonParseException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity handleMethodArgumentException(UnrecognizedPropertyException exception) {
        return ResponseEntity.unprocessableEntity().build();
    }


}
