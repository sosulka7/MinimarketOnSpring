package com.koshelev.spring.web.auth.services;


import com.koshelev.spring.web.auth.dto.NewUserDto;
import com.koshelev.spring.web.auth.entities.Role;
import com.koshelev.spring.web.auth.entities.User;
import com.koshelev.spring.web.auth.exceptions.UserAlreadyExist;
import com.koshelev.spring.web.auth.repositories.RoleRepository;
import com.koshelev.spring.web.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found.", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    public void registrationNewUser(NewUserDto newUserDto){
        if (usernameAndEmailExist(newUserDto.getUsername(), newUserDto.getEmail())){
            throw new UserAlreadyExist("Пользователь с указанной никнеймом или почтой уже существует.");
        }
        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        user.setEmail(newUserDto.getEmail());
        Role role = roleService.getRoleByName("ROLE_USER");
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    private boolean usernameAndEmailExist(String username, String email){
        return userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent();
    }


}
