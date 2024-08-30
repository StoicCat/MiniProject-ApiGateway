package com.miniproject.apigateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway")
public class MainController {
    private final WebClient.Builder webClientBuilder;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        // Manipulasi data untuk hanya menyertakan 3 field yang diperlukan oleh backend service
        Map<String, String> filteredCredentials = new HashMap<>();
        
        // Misalnya, backend service hanya membutuhkan field "username", "password", dan "token"
        filteredCredentials.put("username", credentials.get("username").toString() + (String)credentials.get("password"));
        filteredCredentials.put("token", credentials.get("token"));

        // Mengirim permintaan ke backend service dengan data yang sudah difilter
        return webClientBuilder.build()
            .post()
            .uri("http://localhost:8082/users/login")
            .bodyValue(filteredCredentials)
            .retrieve()
            .bodyToMono(Map.class) // Dapat diganti dengan model yang sesuai
            .map(response -> ResponseEntity.ok(response))
            .block(); // Menunggu hasil
    }
}
