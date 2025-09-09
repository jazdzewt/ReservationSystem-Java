package com.example.reservationsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(csrf -> csrf.disable());
        return http.build();
        /*http
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/").permitAll()
        .anyRequest().authenticated()
    )
    .formLogin(login -> login.permitAll())
    .csrf(csrf -> csrf.disable());*/
    }
}
