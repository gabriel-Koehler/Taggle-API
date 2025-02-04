package com.taggle.taggleapi.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
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

import io.jsonwebtoken.Claims;
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

        List<String> authorities = user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)  // Assume que o método getAuthority retorna uma string como "ROLE_ADMIN"
            .collect(Collectors.toList());

        return jwtEncoder.encode(JwtEncoderParameters.from(
            generateToken(user.getUsername(), authorities)  // Assumindo que o método generateToken retorna um JwtClaimsSet
        )).getTokenValue();
    }
    public JwtClaimsSet generateToken(String subject, List<String> authorities){
        Instant now=Instant.now();
        Long expiresIn=300L;
        return JwtClaimsSet.builder()
            .issuer("taggle_backend")
            .subject(subject)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiresIn))
            .claim("scope",  authorities)
            .build();
    }
    public String refreshToken(String token){
        Jwt jwt=jwtDecoder.decode(token);
          
        return jwtEncoder.encode(JwtEncoderParameters.from(
            generateToken(jwt.getSubject(), (List<String>) jwt.getClaims().get("scope"))
        )).getTokenValue();
    }
    public boolean validateToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token); // Decodifica e verifica a assinatura
            Instant now = Instant.now();
            // System.out.println(jwt.getClaims().get("scope")+"  legal demais meu subject");
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
    public List<String> extractAuthorities(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        Object scope = jwt.getClaims().get("scope");
    
        if (scope instanceof List<?>) {
            return ((List<?>) scope).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}
