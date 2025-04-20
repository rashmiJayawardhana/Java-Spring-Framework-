package com.Java_Spring_Framework.Java_Spring_Framework.service;

import com.Java_Spring_Framework.Java_Spring_Framework.dto.LoginRequestDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.dto.LoginResponseDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.dto.RegisterRequestDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.dto.RegisterResponseDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.entity.UserEntity;
import com.Java_Spring_Framework.Java_Spring_Framework.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity createUser(RegisterRequestDTO userData){
        UserEntity newUser = new UserEntity(
                userData.getName(),
                userData.getEmail(),
                userData.getUsername(),
                passwordEncoder.encode(userData.getPassword())
        );
        return userRepository.save(newUser);
    }

    public LoginResponseDTO login(LoginRequestDTO loginData){
        //Boolean userPresent = isUserEnable(loginData.getUsername());
        //if (!userPresent) return new LoginResponseDTO(null, null, "User not found", "error");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
        } catch (Exception e) {
            return new LoginResponseDTO(null, null, "User not found", "error");
        }

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("role", "User");
        claims.put("email", "company@gmail.com");

        String token = jwtService.getJWTToken(loginData.getUsername(), claims);

        System.out.println(jwtService.getFieldFromToken(token, "role"));
        return new LoginResponseDTO(token, LocalDateTime.now(), null, "Token generate successful");
    }

    public RegisterResponseDTO register(RegisterRequestDTO req){
        if (isUserEnable(req.getUsername())) return new RegisterResponseDTO(null, "User already exists in the system");

        var userData = this.createUser(req);
        if (userData.getId() == null) return new RegisterResponseDTO(null, "System Error");

        return new RegisterResponseDTO(String.format("User registered at %s", userData.getId()), null);
    }

    private Boolean isUserEnable(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
