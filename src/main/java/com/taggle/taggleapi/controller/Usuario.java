package com.taggle.taggleapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.service.UserService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class Usuario {
    private UserService service;

    @GetMapping("/hello")
    public String getMethodName() {
        return "Hello world!";
    }
    @GetMapping("")
    public ResponseEntity<List<UserTaggle>> get() {
        return new ResponseEntity<>(service.getAllUserTaggle(),HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserTaggle> postMethodName() {
        UserTaggle newUser=new UserTaggle();
        newUser.setUsername("Test2");
        newUser.setPassword("123(2)");
        return new ResponseEntity<>(service.saveUserTaggle(newUser),HttpStatus.OK);
    }
    
}
