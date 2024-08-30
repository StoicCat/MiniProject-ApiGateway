package com.miniproject.apigateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.miniproject.apigateway.constant.Environment;
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

<<<<<<< Updated upstream
    private final HealthCheckService healthCheckService;
    private final WebClient.Builder webClientBuilder;

    @PostMapping("/register")
    public Mono<LoginResponseGateway> registerLogin(@RequestBody CreateUserRequestGateway request) {
        return healthCheckService.checkBackendHealth()
                .flatMap(isAvailable -> {
                    if (isAvailable) {
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
                                .uri(Environment.SERVICE_URL  + "users/add-user")
                                .bodyValue(requestAddUser)
                                .retrieve()
                                .bodyToMono(String.class) // Mendapatkan respons dari penambahan pengguna
                                .flatMap(addUserResponse -> {
                                    // Jika menambahkan pengguna berhasil, kirim permintaan login
                                    return webClientBuilder.build()
                                            .post()
                                            .uri(Environment.SERVICE_URL  + "users/login-user")
                                            .bodyValue(request)
                                            .retrieve()
                                            .bodyToMono(LoginResponse.class) // Mendapatkan respons login
                                            .flatMap(loginResponse -> {
                                                // Mengonversi LoginResponse ke LoginResponseGateway
                                                LoginResponseGateway customResponse = new LoginResponseGateway();
                                                customResponse.setToken(loginResponse.getToken());
                                                customResponse.setId(loginResponse.getId());
                                                customResponse.setFirstName(request.getFirstName());
                                                customResponse.setLastName(request.getLastName());
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
                    } else {
                        return Mono.just(new LoginResponseGateway()); // Kembalikan respons kosong atau pesan kesalahan
                    }
                });
    }

    @PostMapping("/login")
    public Mono<ApiResponse<LoginResponseGateway>> login(@RequestBody Map<String, String> request) {
        return healthCheckService.checkBackendHealth()
                .flatMap(isAvailable -> {
                    if (isAvailable) {
                        // Menyiapkan request login dengan username dan password
                        Map<String, String> loginRequest = new HashMap<>();
                        loginRequest.put("email", request.get("email"));
                        loginRequest.put("password", request.get("password"));

                        // Kirim permintaan login dan tunggu respons
                        return webClientBuilder.build()
                                .post()
                                .uri(Environment.SERVICE_URL + "users/login-user")
                                .bodyValue(loginRequest)
                                .retrieve()
                                .bodyToMono(LoginResponse.class)
                                .flatMap(loginResponse -> {
                                    // Membuat ApiResponse
                                    ApiResponse<LoginResponseGateway> apiResponse = new ApiResponse<>();
                                    ApiResponse.Header header = new ApiResponse.Header();
                                    header.setRequestId(UUID.randomUUID().toString()); // Contoh ID permintaan
                                    header.setTimestamp(LocalDateTime.now().toString()); // Contoh timestamp
                                    apiResponse.setHeader(header);

                                    ApiResponse.Status status = new ApiResponse.Status();
                                    status.setCode(HttpStatus.OK.value());
                                    status.setDescription(HttpStatus.OK.getReasonPhrase());
                                    apiResponse.setStatus(status);

                                    LoginResponseGateway content = new LoginResponseGateway();
                                    content.setToken(loginResponse.getToken());
                                    content.setId(loginResponse.getId());

                                    String fullName = loginResponse.getFullName();
                                    String[] nameParts = fullName.split(" ", 2); // Pisahkan hanya pada spasi pertama

                                    content.setFirstName(nameParts.length > 0 ? nameParts[0] : "");
                                    content.setLastName(nameParts.length > 1 ? nameParts[1] : "");
                                    content.setEmail(loginResponse.getEmail());
                                    content.setPhone(loginResponse.getPhone());
                                    content.setAddress(loginResponse.getAddress());
                                    content.setRole(loginResponse.getRole());

                                    apiResponse.setContent(content);

                                    return Mono.just(apiResponse);
                                })
                                .onErrorResume(e -> {
                                    // Tangani kesalahan jika terjadi
                                    e.printStackTrace(); // Menampilkan stack trace untuk debug
                                    
                                    // Membuat ApiResponse untuk kesalahan
                                    ApiResponse<LoginResponseGateway> apiResponse = new ApiResponse<>();
                                    ApiResponse.Header header = new ApiResponse.Header();
                                    header.setRequestId(UUID.randomUUID().toString()); // Contoh ID permintaan
                                    header.setTimestamp(LocalDateTime.now().toString()); // Contoh timestamp
                                    apiResponse.setHeader(header);

                                    ApiResponse.Status status = new ApiResponse.Status();
                                    status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                                    status.setDescription(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                                    apiResponse.setStatus(status);

                                    apiResponse.setErrorMessage("Terjadi kesalahan saat memproses permintaan");

                                    return Mono.just(apiResponse);
                                });
                    } else {
                        // Server tidak tersedia
                        ApiResponse<LoginResponseGateway> apiResponse = new ApiResponse<>();
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
=======
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
>>>>>>> Stashed changes
                })
                .onErrorResume(e -> {
                    // Tangani kesalahan jika terjadi
                    e.printStackTrace(); // Menampilkan stack trace untuk debug
                    return Mono.just(new LoginResponseGateway()); // Kembalikan respons kosong jika terjadi kesalahan
                });
    }
<<<<<<< Updated upstream
=======

    
    @PostMapping("/login")
    public Mono<LoginResponseGateway> login(@RequestBody Map<String, String> request) {

        // URL untuk login
        String url = datasourceServiceUrl + "users/login-user";
        
        // Menyiapkan request login dengan username dan password
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", request.get("email"));
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

    
    
    
>>>>>>> Stashed changes
}
