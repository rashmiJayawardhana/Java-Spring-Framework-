package com.Java_Spring_Framework.Java_Spring_Framework.controller;

import com.Java_Spring_Framework.Java_Spring_Framework.dto.LoginRequestDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.dto.LoginResponseDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.dto.RegisterRequestDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.dto.RegisterResponseDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.entity.UserEntity;
import com.Java_Spring_Framework.Java_Spring_Framework.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
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
    public UserEntity createUser(@RequestBody RegisterRequestDTO user){
        return authService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginData){

        LoginResponseDTO res = authService.login(loginData);
        if (res.getError() != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> login(@RequestBody RegisterRequestDTO req){

        RegisterResponseDTO res = authService.register(req);
        if (res.getError() != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
