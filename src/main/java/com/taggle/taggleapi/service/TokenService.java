package com.taggle.taggleapi.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nimbusds.jwt.JWT;
import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.repository.UserTaggleRepository;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@Service
public class TokenService {
    @Autowired
    private UserTaggleRepository userRepostiRepository;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;
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
            .claim("scope",  user.getAuthorities().toArray()[0].toString() )
            .build();
        // validateToken(jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token); // Decodifica e verifica a assinatura
            Instant now = Instant.now();
            System.out.println(jwt.getSubject()+"  legal demais meu subject");
            // Verifica se o token expirou
            if (jwt.getExpiresAt() == null || jwt.getExpiresAt().isBefore(now)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String extractUsername(String token){
        return jwtDecoder.decode(token).getSubject();
    }
}
