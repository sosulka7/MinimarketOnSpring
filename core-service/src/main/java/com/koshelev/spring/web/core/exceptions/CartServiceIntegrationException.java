package com.koshelev.spring.web.core.exceptions;

public class CartServiceIntegrationException extends RuntimeException{
    public CartServiceIntegrationException(String message){
        super(message);
    }
}
