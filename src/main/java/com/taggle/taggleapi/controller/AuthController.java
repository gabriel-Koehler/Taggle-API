package com.taggle.taggleapi.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taggle.taggleapi.model.DTO.UserTaggle.UserTagglePOST;
import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.service.TokenService;
import com.taggle.taggleapi.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {
    
    private UserService service;

    private TokenService tokenService;
    
    @PostMapping("/signin")
    public ResponseEntity<UserTaggle> registerNewUser(@RequestBody UserTagglePOST userPOST) {
        try {
            return new ResponseEntity<>(service.saveUserTaggle(userPOST),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserTagglePOST loginDTO) {
        try{
            var token = this.tokenService.login(loginDTO.getUsername(), loginDTO.getPassword());
            System.out.println("loginnn");
            return ResponseEntity.ok(Map.of("token", token));
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody String token) {
        try{
            return ResponseEntity.ok(Map.of("token", this.tokenService.refreshToken(token)));
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
