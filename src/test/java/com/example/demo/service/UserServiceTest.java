package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dto.request.UserRegistrationRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Messages;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserRegistrationRequest request;

    private Validator validator;

    @BeforeEach
    void setup() {
        request = new UserRegistrationRequest();
        request.setUsername("lanhuong1712");
        request.setEmail("lanhuong171203@gmail.com");
        request.setPassword("123aBc*/io");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void register_InvalidEmail_ShouldHaveValidationErrors() {
        request.setEmail("null");
        var violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(Messages.REQ_INVALID_FORMAT)));
    }

    @Test
    void return_SaveUser_When_RegisterSuccess() {
        // given
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername(request.getUsername());

        // assertTrue(validator.validate(request).isEmpty());

        when(userRepository.findByUsername(request.getUsername())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_password");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // when
        User result = userService.registerUser(request);
        
        // then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("lanhuong1712", result.getUsername());

        verify(userRepository, times(1)).findByUsername(request.getUsername());
        verify(passwordEncoder, times(1)).encode("123aBc*/io");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void return_Exception_When_UsernameExist() {
        // given
        when(userRepository.findByUsername(request.getUsername())).thenReturn(new User());

        // when then
        DemoException exception = assertThrows(DemoException.class, () ->{
            userService.registerUser(request);
        });

        assertEquals("USR_001", exception.getErrorCode());
        assertEquals(Messages.USER_ALREADY_EXISTS, exception.getMessage());

        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

}
