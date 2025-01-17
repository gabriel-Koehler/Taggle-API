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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserTaggleController {
    private UserService service;

    @GetMapping("/hello")
    public String getMethodName() {
        return "Hello world!";
    }
    @GetMapping("")
    public ResponseEntity<List<UserTaggle>> get() {
        return new ResponseEntity<>(service.getAllUserTaggle(),HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<UserTaggle> registerNewUser() {
        UserTaggle newUser=new UserTaggle();
        newUser.setUsername("Test(for note reference)");
        newUser.setPassword("123(for note reference)");
        return new ResponseEntity<>(service.saveUserTaggle(newUser),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserTaggle> putMethodName(@PathVariable Long id,@RequestBody UserTaggle user) {
        return new ResponseEntity<>(service.putUserTaggle(user,id),HttpStatus.OK);
    }
    
}
