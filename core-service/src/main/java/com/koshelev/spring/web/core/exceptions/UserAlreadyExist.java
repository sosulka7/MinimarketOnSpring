package com.koshelev.spring.web.core.exceptions;

public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(String message){
        super(message);
    }
}
