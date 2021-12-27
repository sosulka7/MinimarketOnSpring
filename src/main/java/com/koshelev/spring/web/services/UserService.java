package com.koshelev.spring.web.services;

import com.koshelev.spring.web.dto.UserDto;
import com.koshelev.spring.web.entities.Role;
import com.koshelev.spring.web.entities.User;
import com.koshelev.spring.web.exceptions.UserAlreadyExist;
import com.koshelev.spring.web.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

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

    public User registrationNewUser(UserDto userDto){
        if (emailExist(userDto.getEmail())){
            throw new UserAlreadyExist(String.format("Пользователь с почтой '%s' уже существует.", userDto.getEmail()));
        }
        if (usernameExist(userDto.getUsername())){
            throw new UserAlreadyExist(String.format("Пользователь с логином '%s' уже существует.", userDto.getUsername()));
        }

        //Здесь должны быть хэширование пароля, конвертация Dto в Entity и сохранение Usera в бд.
        //После чего возвращаем User'a или UserDto
        //Также не доделал саму конвертацию. Еще под вопросом - правильность UserDto.
        return new User();
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email) != null;
    }

    private boolean usernameExist(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
