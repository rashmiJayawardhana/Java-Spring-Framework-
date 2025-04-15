package com.Java_Spring_Framework.Java_Spring_Framework.controller;

import com.Java_Spring_Framework.Java_Spring_Framework.entity.UserEntity;
import com.Java_Spring_Framework.Java_Spring_Framework.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return authService.getAllUsers();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user){
        return authService.createUser(user);
    }
}
