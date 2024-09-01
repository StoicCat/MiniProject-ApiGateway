package com.miniproject.apigateway.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.miniproject.apigateway.model.response.ApiResponse;
import com.miniproject.apigateway.model.response.LoginResponseGateway;

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
    
    public <T> Mono<ApiResponse<T>> getErrorMessage(Class<T> clazz) {
    	ApiResponse<T> apiResponse = new ApiResponse<T>();
		ApiResponse.Header header = new ApiResponse.Header();
		header.setRequestId(UUID.randomUUID().toString()); // Contoh ID permintaan
		header.setTimestamp(LocalDateTime.now().toString()); // Contoh timestamp
		apiResponse.setHeader(header);

		ApiResponse.Status status = new ApiResponse.Status();
		status.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		status.setDescription(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
		apiResponse.setStatus(status);

		apiResponse.setErrorMessage("Server sedang dalam gangguan");
		
    	return Mono.just(apiResponse);
    }
}
