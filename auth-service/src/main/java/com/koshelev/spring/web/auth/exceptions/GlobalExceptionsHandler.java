package com.koshelev.spring.web.auth.exceptions;

import com.koshelev.spring.web.api.exceptions.AppError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> catchUserAlreadyExistException(UserAlreadyExist e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserValidationsError> catchValidationException(UserValidationsException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new UserValidationsError(e.getErrorFieldsMessages()), HttpStatus.BAD_REQUEST);
    }

}
