package com.thuctaptotnghiem.thuctaptotnghiep.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
