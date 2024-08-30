package com.miniproject.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf().disable() // Menonaktifkan CSRF di API Gateway
            .authorizeExchange()
                .anyExchange().permitAll(); // Mengizinkan semua permintaan (sesuaikan sesuai kebutuhan Anda)

        return http.build();
    }
}
