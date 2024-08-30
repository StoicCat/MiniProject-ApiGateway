package com.miniproject.apigateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.miniproject.apigateway.model.request.CreateUserRequest;
import com.miniproject.apigateway.model.request.CreateUserRequestGateway;
import com.miniproject.apigateway.model.response.LoginResponse;
import com.miniproject.apigateway.model.response.LoginResponseGateway;

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
    public Mono<LoginResponseGateway> registerLogin(@RequestBody CreateUserRequestGateway request) {

        String url_1 = datasourceServiceUrl + "users/add-user";
        String url_2 = datasourceServiceUrl + "users/login-user";
        
        // Menyiapkan request untuk menambahkan pengguna
        CreateUserRequest requestAddUser = new CreateUserRequest();
        requestAddUser.setAddress(request.getAddress());
        requestAddUser.setEmail(request.getEmail());
        requestAddUser.setPassword(request.getPassword());
        requestAddUser.setPhone(request.getPhone());
        requestAddUser.setRole(request.getRole());
        requestAddUser.setFullName(request.getFirstName() + " " + request.getLastName());

        // Kirim permintaan untuk menambahkan pengguna
        return webClientBuilder.build()
                .post()
                .uri(url_1)
                .bodyValue(requestAddUser)
                .retrieve()
                .bodyToMono(String.class) // Mendapatkan respons dari penambahan pengguna
                .flatMap(addUserResponse -> {
                    // Jika menambahkan pengguna berhasil, kirim permintaan login
                    return webClientBuilder.build()
                            .post()
                            .uri(url_2)
                            .bodyValue(request)
                            .retrieve()
                            .bodyToMono(LoginResponse.class) // Mendapatkan respons login
                            .flatMap(loginResponse -> {
                                // Mengonversi LoginResponse ke LoginResponseGateway
                                LoginResponseGateway customResponse = new LoginResponseGateway();
                                customResponse.setToken(loginResponse.getToken());
                                customResponse.setId(loginResponse.getId());
                                customResponse.setFirstName(request.getFirstName()); // Atur sesuai data request atau loginResponse
                                customResponse.setLastName(request.getLastName()); // Atur sesuai data request atau loginResponse
                                customResponse.setEmail(loginResponse.getEmail());
                                customResponse.setPhone(loginResponse.getPhone());
                                customResponse.setAddress(loginResponse.getAddress());
                                customResponse.setRole(loginResponse.getRole());

                                return Mono.just(customResponse); // Kembalikan respons yang telah dikonversi
                            });
                })
                .onErrorResume(e -> {
                    // Tangani kesalahan jika terjadi
                    e.printStackTrace(); // Menampilkan stack trace untuk debug
                    return Mono.just(new LoginResponseGateway()); // Kembalikan respons kosong jika terjadi kesalahan
                });
    }

    
    @PostMapping("/login")
    public Mono<LoginResponseGateway> login(@RequestBody Map<String, String> request) {

        // URL untuk login
        String url = datasourceServiceUrl + "users/login-user";
        
        // Menyiapkan request login dengan username dan password
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", request.get("username"));
        loginRequest.put("password", request.get("password"));

        // Kirim permintaan login dan tunggu respons
        return webClientBuilder.build()
                .post()	// 1. Menentukan metode HTTP POST
                .uri(url)   // 2. Menentukan URI endpoint
                .bodyValue(loginRequest) // 3. Mengatur body permintaan
                .retrieve()  // 4. Mengambil respons dari server
                .bodyToMono(LoginResponse.class).flatMap(loginResponse-> {
                	
                    LoginResponseGateway customResponse = new LoginResponseGateway();
                    customResponse.setToken(loginResponse.getToken());
                    customResponse.setId(loginResponse.getId());
                    
                    String fullName = loginResponse.getFullName();
                    String[] nameParts = fullName.split(" ", 2); // Pisahkan hanya pada spasi pertama

                    
                    customResponse.setFirstName(nameParts[0]);// Atur sesuai data request atau loginResponse
                    customResponse.setLastName(nameParts[1]); // Atur sesuai data request atau loginResponse
                    customResponse.setEmail(loginResponse.getEmail());
                    customResponse.setPhone(loginResponse.getPhone());
                    customResponse.setAddress(loginResponse.getAddress());
                    customResponse.setRole(loginResponse.getRole());

                    return Mono.just(customResponse); 
                	
                	
                }) // 5. Mengonversi body respons menjadi Mono<LoginResponse>
                .onErrorResume(e -> {
                    // Tangani kesalahan jika terjadi
                    // Misalnya, log kesalahan dan kembalikan respons kosong atau pesan kesalahan
                    e.printStackTrace(); // Menampilkan stack trace untuk debug
                    return Mono.just(new LoginResponseGateway()); // Kembalikan respons kosong jika login gagal
                });
    }

    
    
    
}
