package com.spring.navesespaciales_api.navesespaciales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/v2/api-docs", "/swagger-resources/**", "/webjars/**", "/api/v1/naves/**").permitAll()
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                .csrf(csrf -> csrf.disable()) // Permite todas las peticiones sin autenticación
                .httpBasic(withDefaults()); 
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withDefaultPasswordEncoder()
                .username("hola")
                .password("hola")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}