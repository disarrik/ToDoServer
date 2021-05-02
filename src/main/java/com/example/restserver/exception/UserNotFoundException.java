package com.example.restserver.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
