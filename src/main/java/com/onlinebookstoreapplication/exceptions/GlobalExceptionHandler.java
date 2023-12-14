package com.onlinebookstoreapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> bookStoreGlobalExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage())
                );
        return  errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public Map<String, Object> bookStoreGlobalExceptionHandler(BookNotFoundException ex){
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return  errors;
    }
}
