package com.koshelev.spring.web.cart.exceptions;

public class NoConnectionToCoreServiceException extends RuntimeException{

    public NoConnectionToCoreServiceException(String message){
        super(message);
    }

}
