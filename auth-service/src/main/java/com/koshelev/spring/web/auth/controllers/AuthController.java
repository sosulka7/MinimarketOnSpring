package com.koshelev.spring.web.auth.controllers;


import com.koshelev.spring.web.api.exceptions.AppError;
import com.koshelev.spring.web.auth.dto.JwtRequest;
import com.koshelev.spring.web.auth.dto.JwtResponse;
import com.koshelev.spring.web.auth.dto.NewUserDto;
import com.koshelev.spring.web.auth.services.UserService;
import com.koshelev.spring.web.auth.utils.JwtTokenUtil;
import com.koshelev.spring.web.auth.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserValidator userValidator;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.toString(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public void registrations(@RequestBody NewUserDto userRegDto){
        userValidator.validate(userRegDto);
        userService.registrationNewUser(userRegDto);
    }
}
