package com.koshelev.spring.web.auth.services;

import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.auth.entities.Role;
import com.koshelev.spring.web.auth.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleByName(String name){
        return roleRepository.findRoleByName(name).orElseThrow(()-> new ResourceNotFoundException("Role not found"));
    }

}
