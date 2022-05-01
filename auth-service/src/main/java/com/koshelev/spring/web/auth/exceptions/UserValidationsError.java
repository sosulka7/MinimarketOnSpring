package com.koshelev.spring.web.auth.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserValidationsError {
    private List<String> errorFieldsMessages;

    public UserValidationsError(List<String> errorFieldsMessages) {
        this.errorFieldsMessages = errorFieldsMessages;
    }
}
