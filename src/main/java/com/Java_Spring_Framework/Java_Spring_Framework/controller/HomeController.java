package com.Java_Spring_Framework.Java_Spring_Framework.controller;

import com.Java_Spring_Framework.Java_Spring_Framework.service.JWTService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    public HomeController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    private final JWTService jwtService;

    @GetMapping
    public String getHello(){
        return "hi";
    }

    @PostMapping("/login")
    public String login(){
        return jwtService.getJWTToken();
    }

    @GetMapping("/username")
    public String getUsername(@RequestParam String token){
        return jwtService.getUsername(token);
    }
}
