package com.miniproject.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.miniproject.apigateway.model.request.CreateUserRequest;
import com.miniproject.apigateway.model.request.CreateUserRequestGateway;
import com.miniproject.apigateway.model.response.LoginResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String datasourceServiceUrl = "http://localhost:8082/";

    @PostMapping("/register")
    public Mono<LoginResponse> registerLogin(@RequestBody CreateUserRequestGateway request) {

        String url_1 = datasourceServiceUrl + "users/add-user";
        String url_2 = datasourceServiceUrl + "users/login-user";
        
        CreateUserRequest requestAddUser = new CreateUserRequest();
        requestAddUser.setAddress(request.getAddress());
        requestAddUser.setEmail(request.getEmail());
        requestAddUser.setPassword(request.getPassword());
        requestAddUser.setPhone(request.getPhone());
        requestAddUser.setRole(request.getRole());
        requestAddUser.setFullName(request.getFirstName() + " " + request.getLastName());

        // Kirim permintaan untuk menambahkan pengguna dan tunggu respons
        return webClientBuilder.build()
                .post()
                .uri(url_1)
                .bodyValue(requestAddUser)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(addUserResponse -> {
                    // Jika menambahkan pengguna berhasil, kirim permintaan login
                    return webClientBuilder.build()
                            .post()
                            .uri(url_2)
                            .bodyValue(request)
                            .retrieve()
                            .bodyToMono(LoginResponse.class);
                })
                .onErrorResume(e -> {
                    // Tangani kesalahan jika terjadi
                    return Mono.just(new LoginResponse());
                });
    }
}
