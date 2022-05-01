package com.koshelev.spring.web.auth.exceptions;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserValidationsException  extends RuntimeException {
    private List<String> errorFieldsMessages;

    public UserValidationsException(List<String> errorFieldsMessages) {
        super(errorFieldsMessages.stream().collect(Collectors.joining(", ")));
        this.errorFieldsMessages = errorFieldsMessages;
    }
}
