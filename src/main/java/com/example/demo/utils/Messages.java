package com.example.demo.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Messages {

    // Common
    public static final String SUCCESS = "Operation completed successfully.";
    public static final String FAILURE = "Operation failed. Please try again.";
    public static final String DATA_NOT_FOUND = "The requested data could not be found.";
    public static final String INTERNAL_SERVER_ERROR = "An unexpected error occurred. Please contact the administrator.";
    public static final String INVALID_REQUEST = "The request contains invalid data.";
    public static final String DELETE_SUCCESS = "Resource deleted successfully.";
    public static final String UPDATE_SUCCESS = "Resource updated successfully.";


    // Authen Author
    public static final String REGISTER_SUCCESS = "Register successful. Welcome!";
    public static final String REGISTER_FAILED = "Register failed!";
    public static final String LOGIN_SUCCESS = "Login successful. Welcome back!";
    public static final String LOGIN_FAILED = "Invalid username or password.";
    public static final String ACCESS_DENIED = "You do not have permission to access this resource.";
    public static final String TOKEN_EXPIRED = "Your session has expired. Please log in again.";
    public static final String TOKEN_INVALID = "Invalid security token.";
    public static final String UNAUTHORIZED = "Authentication is required to access this resource.";

    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_ALREADY_EXISTS = "User with this email/username already exists.";
    public static final String CHANGE_PASSWORD_SUCCESS = "Your password has been changed successfully.";
    public static final String OLD_PASSWORD_INCORRECT = "The old password you entered is incorrect.";
    public static final String PASSWORD_SAME_AS_OLD = "New password cannot be the same as your current password.";
    public static final String PASSWORD_MISMATCH = "The password confirmation does not match.";

    // General
    public static final String REQ_FIELD_REQUIRED = "This field is mandatory.";
    public static final String REQ_INVALID_FORMAT = "Invalid data format.";
    
    // User Info
    public static final String VALID_NAME_REQUIRED = "Full name must not be blank.";
    public static final String VALID_NAME_SIZE = "Full name must be between 2 and 50 characters.";
    public static final String VALID_EMAIL_REQUIRED = "Email address is required.";
    public static final String VALID_EMAIL_FORMAT = "Please provide a valid email address.";
    public static final String VALID_PHONE_FORMAT = "Phone number must be exactly 10 digits.";
    
    // Credentials
    public static final String VALID_USERNAME_REQUIRED = "Username is required.";
    public static final String VALID_PASSWORD_REQUIRED = "Password is required.";
    public static final String VALID_PASSWORD_SIZE = "Password must be at least 8 characters long.";
    public static final String VALID_PASSWORD_COMPLEX = "Password must contain at least one uppercase letter, one number, and one special character.";
}
