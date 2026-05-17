package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ControllerAdviceConfig{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> hendleUserNotFound(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> hendleGeneralError(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fatal error");
    }
    @ExceptionHandler
    public ResponseEntity<String> hendlResponseEntity(ExceptionInInitializerError ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bed Requst, try agaen");
    }
}