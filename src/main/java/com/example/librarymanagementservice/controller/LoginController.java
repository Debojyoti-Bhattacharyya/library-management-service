package com.example.librarymanagementservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class LoginController {

    @GetMapping("/login")
    @PreAuthorize("authenticated")
    public Mono<String> login() {
        return Mono.just("Login Successful for the User");
    }

    @GetMapping("/adminHome")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Mono<String> adminHome() {
        return Mono.just("Login Successful for the ADMIN");
    }
}
