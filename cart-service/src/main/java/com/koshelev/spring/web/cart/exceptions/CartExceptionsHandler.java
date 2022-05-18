package com.koshelev.spring.web.cart.exceptions;

import com.koshelev.spring.web.api.exceptions.AppError;
import com.koshelev.spring.web.api.exceptions.CartServiceAppError;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class CartExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new CartServiceAppError(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CoreServiceIntegrationException.class)
    public ResponseEntity<AppError> catchNoConnectionToCoreServiceException(CoreServiceIntegrationException e) {
        return new ResponseEntity<>(new CartServiceAppError(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
