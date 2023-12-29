package com.products.inventoryservice.controllers;

import com.products.inventoryservice.exception.InventoryAlreadyExistException;
import com.products.inventoryservice.exception.InventoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class InventoryControllerAdvice {

    @ExceptionHandler(InventoryAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String inventoryAlreadyExistExceptionHandler(InventoryAlreadyExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String inventoryNotFoundExceptionHandler(InventoryNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
       var errors = new HashMap<String, String>();
       ex.getBindingResult().getAllErrors().forEach(error -> {
           String field = ((FieldError) error).getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(field, errorMessage);
       });
       return errors;
    }
}
