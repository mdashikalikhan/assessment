package com.assessment.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User not found. Id: " + id);
    }
}
