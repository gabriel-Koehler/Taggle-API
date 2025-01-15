package com.taggle.taggleapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.taggle.taggleapi.model.UserTaggle;
import com.taggle.taggleapi.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
@RequestMapping("/")
public class Usuario {
    private UserService service;

    @GetMapping("/hello")
    public String getMethodName() {
        return "Hello world!";
    }
    @GetMapping("/usuario")
    public String get() {
        return null;
    }
    @PostMapping("/login")
    public String postMethodName() {
        return service.saveUserTaggle(new UserTaggle("username","password"));
    }
    
}
