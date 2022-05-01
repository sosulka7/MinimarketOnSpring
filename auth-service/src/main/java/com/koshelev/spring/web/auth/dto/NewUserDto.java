package com.koshelev.spring.web.auth.dto;

import lombok.Data;

@Data
public class NewUserDto {
    private String username;
    private String email;
    private String password;

    public NewUserDto(){}
}
