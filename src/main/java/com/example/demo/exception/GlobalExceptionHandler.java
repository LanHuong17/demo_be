package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.respone.DemoResponseEntity;
import com.example.demo.utils.GenerateResponse;
import com.example.demo.utils.Messages;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DemoException.class)
    public ResponseEntity<DemoResponseEntity> handleBusiness(DemoException ex) {
        return GenerateResponse.generateErrorResponse(ex.getErrorCode(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DemoResponseEntity> handleAll(Exception ex) {
        return GenerateResponse.generateErrorResponse("500", Messages.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
