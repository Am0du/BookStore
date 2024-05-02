package com.example.book.store.rest.security;

import com.example.book.store.rest.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.PasswordAuthentication;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//

        http.authorizeHttpRequests(configure ->
                configure

                        .requestMatchers(HttpMethod.POST, "api/v1/users/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/users/signin").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/v1/users").hasAnyRole("USER", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/users").hasAnyRole("USER", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "api/v1/users").hasAnyRole("USER", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "api/v1/users/**").hasAnyRole("USER", "MANAGER")

        );


        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
