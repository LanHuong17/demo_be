package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Messages;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.UserRegistrationRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.DemoException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; 

    @Transactional
    public User registerUser(UserRegistrationRequest request) {
        
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new DemoException("USR_001", Messages.USER_ALREADY_EXISTS);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword); 
        
        return userRepository.save(user);
    }
}
    

