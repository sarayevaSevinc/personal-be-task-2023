package com.sarayeva.cybercubebetask.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("User not found");
    }
}
