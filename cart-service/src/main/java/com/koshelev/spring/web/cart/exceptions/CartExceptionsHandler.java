package com.koshelev.spring.web.cart.exceptions;

import com.koshelev.spring.web.api.exceptions.AppError;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
@ResponseBody
public class CartExceptionsHandler {

    @ExceptionHandler(NoConnectionToCoreServiceException.class)
    public ResponseEntity<AppError> coreServiceConnectionError(NoConnectionToCoreServiceException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> resourceNotFoundError(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
