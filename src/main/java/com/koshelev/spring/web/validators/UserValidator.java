package com.koshelev.spring.web.validators;

import com.koshelev.spring.web.dto.UserDto;
import com.koshelev.spring.web.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public void validate(UserDto userDto){
        List<String> errors = new ArrayList<>();
        //пока простая валидация на пустые поля
        //по-хорошему у логина, пароля и почты нужно делать проверку на недопустимые символы (или что-то в этом роде)
        if(userDto.getUsername().isBlank()){
            errors.add("Логин не может быть пустым");
        }
        if (userDto.getPassword().isBlank()){
            errors.add("Пароль не может быть пустым");
        }
        if (userDto.getEmail().isBlank()){
            errors.add("Почта не может быть пуста");
        }
        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
