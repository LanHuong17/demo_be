package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;

import com.example.demo.utils.Messages;
import com.example.demo.utils.Regex;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    @NotBlank(message = Messages.REQ_FIELD_REQUIRED)
    private String username;

    @NotBlank(message = Messages.REQ_FIELD_REQUIRED)
    @Email(message = Messages.REQ_INVALID_FORMAT)
    private String email;

    @NotBlank(message = Messages.REQ_FIELD_REQUIRED)
    @Size(min = 8, message = Messages.VALID_PASSWORD_SIZE)
    @Pattern(regexp = Regex.PASSWORD_REGEX, message = Messages.VALID_PASSWORD_COMPLEX)
    private String password;
}