package com.example.restserver.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
