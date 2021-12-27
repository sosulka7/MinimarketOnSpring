package com.koshelev.spring.web.controllers;


import com.koshelev.spring.web.dto.UserDto;
import com.koshelev.spring.web.services.UserService;
import com.koshelev.spring.web.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserValidator userValidator;
    private final UserService userService;

    @PostMapping("/registration")
    public UserDto registrationNewUser (@RequestBody UserDto userDto){
        userValidator.validate(userDto);
        userService.registrationNewUser(userDto);
        return null;
    }
}
