package com.example.reservationsystem.config;

import com.example.reservationsystem.service.AdminDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final AdminDetailsService adminDetailsService;

    public SecurityConfig(AdminDetailsService adminDetailsService) {
        this.adminDetailsService = adminDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // publiczne strony
                        .requestMatchers("/", "/login", "/error").permitAll()
                        // tylko admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // wszystko inne wymaga logowania
                        .anyRequest().authenticated()
                )
                // formularz logowania
                .formLogin(form -> form
                        .loginPage("/login")              // customowa strona logowania
                        .defaultSuccessUrl("/admin", true) // po udanym logowaniu
                        .permitAll()
                )
                // wylogowanie
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // jeśli chcesz wyłączyć CSRF w dev

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // hashowanie haseł
    }

        /*http
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/").permitAll()
        .anyRequest().authenticated()
    )
    .formLogin(login -> login.permitAll())
    .csrf(csrf -> csrf.disable());*/
}

