package com.koshelev.spring.web.auth.validators;

import com.koshelev.spring.web.auth.dto.NewUserDto;
import com.koshelev.spring.web.auth.exceptions.UserValidationsException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {
    //очень упрощенная валидация на проверку пустоты полей
    public void validate(NewUserDto newUserDto){
        List<String> errors = new ArrayList<>();
        if (newUserDto.getUsername() == null){
            errors.add("Никнейм не может быть пустым.");
        }
        if (newUserDto.getEmail() == null){
            errors.add("Почта не может быть пустой");
        }
        if (newUserDto.getPassword() == null){
            errors.add("Пароль не может быть пустым");
        }
        if (!errors.isEmpty()){
            throw new UserValidationsException(errors);
        }
    }
}
