package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.respone.DemoResponseEntity;

public class GenerateResponse {
    
    public static ResponseEntity<DemoResponseEntity> generateResponse(boolean hasNoError, Object response, HttpStatus statusCode) {
        DemoResponseEntity demoResponseEntity = new DemoResponseEntity(hasNoError, "", response);
        String code = String.valueOf(statusCode.value());
        demoResponseEntity.setCode(hasNoError ? code : "ERR_" + code);
        
        return new ResponseEntity<>(demoResponseEntity, statusCode);
    }
    
    public static ResponseEntity<DemoResponseEntity> generateErrorResponse(String errorCode, String message, HttpStatus status) {
        DemoResponseEntity responseBody = new DemoResponseEntity("FAILURE", errorCode, message);
        return new ResponseEntity<>(responseBody, status);
    }
}
