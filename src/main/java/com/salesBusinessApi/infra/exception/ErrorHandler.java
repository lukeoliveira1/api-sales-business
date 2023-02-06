package com.salesBusinessApi.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //handle exceptions
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class) //error 404
    public ResponseEntity handleError404() { return ResponseEntity.notFound().build(); }

    @ExceptionHandler(MethodArgumentNotValidException.class) //error 400
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors(); //list erros of exception

        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorsValidation::new).toList());
    }

    private record DataErrorsValidation(String input, String message) {
        public DataErrorsValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}