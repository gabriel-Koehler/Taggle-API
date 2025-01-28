package com.taggle.taggleapi.service;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.repository.UserTaggleRepository;

import lombok.AllArgsConstructor;

@Service
public class TokenService {
    @Autowired
    private UserTaggleRepository userRepostiRepository;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(String username,String password) {
        UserTaggle user= userRepostiRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username not found"));
        
        Boolean isCorrect=user.isLoginCorrect(password,bCryptPasswordEncoder);
        if(!isCorrect){
            throw new BadCredentialsException("Username or Password Invalid");
        }

        Instant now=Instant.now();
        Long expiresIn=300L;
        JwtClaimsSet claims=JwtClaimsSet.builder()
            .issuer("taggle_backend")
            .subject(user.getUsername())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiresIn))
            .claim("scope", user.getAuthorities())
            .build();
        
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
