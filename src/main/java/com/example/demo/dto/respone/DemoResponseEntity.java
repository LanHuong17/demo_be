package com.example.demo.dto.respone;

import lombok.Data;

@Data
public class DemoResponseEntity {
    private String status;
    private String code;
    private Object response;

    public DemoResponseEntity() {
        this.status = "SUCCESS";
        this.code = "200";
        this.response = "";
    }

    public DemoResponseEntity(Object response) {
        this.response = response;
    }

    public DemoResponseEntity(String status, String code, Object response) {
        this.status = status;
        this.code = code;
        this.response = response;
    }

    public DemoResponseEntity(boolean isSuccessful, String code, Object response) {
        this.status = isSuccessful ? "SUCCESS" : "FAILURE";
        this.code = code;
        this.response = response;
    }
}
