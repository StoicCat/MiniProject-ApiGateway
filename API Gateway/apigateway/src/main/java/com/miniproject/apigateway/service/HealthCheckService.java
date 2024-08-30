package com.miniproject.apigateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HealthCheckService {

    private final WebClient.Builder webClientBuilder;
    private final String healthCheckUrl = "http://localhost:8082/actuator/health";

    public HealthCheckService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<Boolean> checkBackendHealth() {
        return webClientBuilder.build()
                .get()
                .uri(healthCheckUrl)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .onErrorReturn(false); // Menandakan server tidak tersedia jika terjadi kesalahan
    }
}
