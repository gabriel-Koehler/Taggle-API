package com.taggle.taggleapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.taggle.taggleapi.model.DTO.UserTaggle.UserTagglePOST;
import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.service.UserService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class UserTaggleController {
    @Autowired
    private UserService service;

    @GetMapping("/hello")
    public String getMethodName() {
        return "Hello world!";
    }
    @GetMapping("")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<UserTaggle>> get() {
        return new ResponseEntity<>(service.getAllUserTaggle(),HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserTaggle> putMethodName(@PathVariable Long id,@RequestBody UserTaggle user) {
        return new ResponseEntity<>(service.putUserTaggle(user,id),HttpStatus.OK);
    }
    
}
