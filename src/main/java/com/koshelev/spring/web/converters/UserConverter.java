package com.koshelev.spring.web.converters;

import com.koshelev.spring.web.dto.UserDto;
import com.koshelev.spring.web.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User dtoToEntity(UserDto userDto){
        return new User();
    }

    public UserDto entityToDto(User user){
        return new UserDto();
    }
}
