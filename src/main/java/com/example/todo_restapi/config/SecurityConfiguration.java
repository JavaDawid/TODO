package com.example.todo_restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/register", "/confirmation", "/change-password").permitAll()
                .requestMatchers("/task/**").authenticated()
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form
                .loginPage("/login").permitAll()
        );
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**"))
                .logoutSuccessUrl("/")
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
