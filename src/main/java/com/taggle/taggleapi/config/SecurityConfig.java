package com.taggle.taggleapi.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.taggle.taggleapi.service.TokenService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ComponentScan(basePackages = "com.taggle.taggleapi")
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,TokenService tokenService) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/signin").permitAll()
                        .requestMatchers(HttpMethod.GET,"/v3/api-docs").permitAll()
                        .requestMatchers(HttpMethod.GET,"/swagger-ui/index.html").permitAll()
                        .requestMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST,"/swagger-ui/index.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/hello").permitAll()
                        .anyRequest().authenticated()
                        )
                    .addFilterBefore(new JwtAuthenticationFilter(tokenService), 
                    org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .oauth2ResourceServer(config -> config.jwt(jwt -> jwt.decoder(jwtDecoder())));

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder bPasswordEncoder() {
        System.out.println(publicKey);
        System.out.println(privateKey);
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        System.out.println("Criando JwtEncoder com chave pública: " + publicKey);
        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(
                        new JWKSet(
                                new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build())));
    }

    @Bean
    JwtDecoder jwtDecoder() {
        System.out.println("Criando JwtEncoder com chave pública: " + publicKey);
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
    @Bean
JwtAuthenticationFilter jwtAuthenticationFilter(TokenService tokenService) {
    return new JwtAuthenticationFilter(tokenService);
}

}
