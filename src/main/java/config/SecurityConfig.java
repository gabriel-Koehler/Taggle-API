package config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Value("${}")
    private RSAPublicKey publicKey;

    @Value("${}")
    private RSAPrivateKey privateKey;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests(auth -> auth.requestMatchers(HttpMethod.POST,"/signin").permitAll()
            .requestMatchers(HttpMethod.POST,"/login").permitAll()
            )

        return http.build();
    }

}
