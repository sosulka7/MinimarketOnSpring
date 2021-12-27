package com.koshelev.spring.web.dto;


import com.koshelev.spring.web.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String email;
    private Collection<Role> roles;

}
