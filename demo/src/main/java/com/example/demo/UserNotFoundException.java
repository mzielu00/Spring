package com.example.demo;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("nie ma " + id);
    }
    public UserNotFoundException(String name) {
        super("nie ma " + name);
    }
}
