package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.UserRegistrationRequest;
import com.example.demo.dto.respone.DemoResponseEntity;
import com.example.demo.dto.respone.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.utils.GenerateResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.entity.User;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<DemoResponseEntity> register(@Valid @RequestBody UserRegistrationRequest request) {
        
        User user = userService.registerUser(request);

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );

        return GenerateResponse.generateResponse(
                true, 
                userResponse, 
                HttpStatus.CREATED
        );
    }
}