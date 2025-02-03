package com.taggle.taggleapi.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.taggle.taggleapi.service.TokenService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/signin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/hello").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                        )
                    .addFilterBefore(new JwtAuthenticationFilter(), 
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
        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(
                        new JWKSet(
                                new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build())));
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
    //   @Bean
    // public JwtAuthenticationConverter jwtAuthenticationConverter() {
    //     JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    //     converter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthoritiesConverter() {
    //         @Override
    //         public Collection<GrantedAuthority> convert(Jwt jwt) {
    //             // Supondo que as authorities estão dentro do claim "scope" (ajuste conforme sua necessidade)
    //             Object authorities = jwt.getClaims().get("scope");
    //             if (authorities instanceof List) {
    //                 List<?> roles = (List<?>) authorities;
    //                 return roles.stream()
    //                     .map(role -> new SimpleGrantedAuthority((String) role))
    //                     .collect(Collectors.toList());
    //             } else if (authorities instanceof String) {
    //                 return Collections.singletonList(new SimpleGrantedAuthority((String) authorities));
    //             }
    //             return Collections.emptyList();
    //         }
    //     });
    //     return converter;
    // }
}
