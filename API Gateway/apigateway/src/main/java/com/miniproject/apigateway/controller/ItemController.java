package com.miniproject.apigateway.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.miniproject.apigateway.constant.Environment;
import com.miniproject.apigateway.model.request.CreateUserRequest;
import com.miniproject.apigateway.model.response.ApiResponse;
import com.miniproject.apigateway.model.response.InStockItemResponse;
import com.miniproject.apigateway.model.response.LoginResponse;
import com.miniproject.apigateway.model.response.LoginResponseGateway;
import com.miniproject.apigateway.model.response.SupplierItemResponse;
import com.miniproject.apigateway.service.HealthCheckService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("gateway")
@RequiredArgsConstructor
public class ItemController {

    private final HealthCheckService healthCheckService;
	private final WebClient.Builder webClientBuilder;

	@GetMapping("supplier-items")
	public Mono<ResponseEntity<?>> getSupplierItems(@RequestHeader("IniBukanKunci") String authorizationHeader) {
		return healthCheckService.checkBackendHealth()
                .flatMap(isAvailable -> {
                	if (isAvailable) {
                        return webClientBuilder.build().get().uri(Environment.SERVICE_URL+"supplier-item/get-all-supplier-item")
                				.headers(headers -> headers.setBearerAuth(
        						authorizationHeader))
        				.retrieve()
        				.bodyToMono(new ParameterizedTypeReference<List<SupplierItemResponse>>() {})
        				.flatMap(response -> {
        					ApiResponse<List<SupplierItemResponse>> apiResponse = new ApiResponse<>();	
                            ApiResponse.Header header = new ApiResponse.Header();
                            header.setRequestId(UUID.randomUUID().toString()); 
                            header.setTimestamp(LocalDateTime.now().toString()); 
                            apiResponse.setHeader(header);
                            
                            ApiResponse.Status status = new ApiResponse.Status();
                            status.setCode(HttpStatus.OK.value());
                            status.setDescription(HttpStatus.OK.getReasonPhrase());
                            apiResponse.setStatus(status);
                            
                            apiResponse.setContent(response);
                            
                            return Mono.just(apiResponse);
        				})
        				.map(ResponseEntity::ok);
                    } else {
                        return Mono.just(new ResponseEntity<>(new LoginResponseGateway(), HttpStatus.OK));
                    }
                });
	}
	
	@GetMapping("supplier-items/{id}")
	public Mono<ResponseEntity<?>> getSupplierItem(
			@RequestHeader("IniBukanKunci") String authorizationHeader,
			@PathVariable String id) {
		return healthCheckService.checkBackendHealth()
                .flatMap(isAvailable -> {
                	if (isAvailable) {
                        return webClientBuilder.build().get().uri(Environment.SERVICE_URL+"supplier-item/get-supplier-item-by-id/"+id)
                				.headers(headers -> headers.setBearerAuth(
        						authorizationHeader))
        				.retrieve()
        				.bodyToMono(new ParameterizedTypeReference<SupplierItemResponse>() {})
        				.flatMap(response -> {
        					ApiResponse<SupplierItemResponse> apiResponse = new ApiResponse<>();	
                            ApiResponse.Header header = new ApiResponse.Header();
                            header.setRequestId(UUID.randomUUID().toString()); 
                            header.setTimestamp(LocalDateTime.now().toString()); 
                            apiResponse.setHeader(header);
                            
                            ApiResponse.Status status = new ApiResponse.Status();
                            status.setCode(HttpStatus.OK.value());
                            status.setDescription(HttpStatus.OK.getReasonPhrase());
                            apiResponse.setStatus(status);
                            
                            apiResponse.setContent(response);
                            
                            return Mono.just(apiResponse);
        				})
        				.map(ResponseEntity::ok);
                    } else {
                        return Mono.just(new ResponseEntity<>(new LoginResponseGateway(), HttpStatus.OK));
                    }
                });
	}
	
//	@PostMapping("supplier-items")
//	public Mono<ResponseEntity<?>> addSupplierItems(@RequestHeader("IniBukanKunci") String authorizationHeader) {
//		
//	}
	
	@GetMapping("in-stock-items")
	public Mono<ResponseEntity<?>> getInStockItems(@RequestHeader("IniBukanKunci") String authorizationHeader) {
		return healthCheckService.checkBackendHealth()
                .flatMap(isAvailable -> {
                	if (isAvailable) {
                        return webClientBuilder.build().get().uri(Environment.SERVICE_URL+"instock-item/get-all-items")
                				.headers(headers -> headers.setBearerAuth(
        						authorizationHeader))
        				.retrieve()
        				.bodyToMono(new ParameterizedTypeReference<List<InStockItemResponse>>() {})
        				.flatMap(response -> {
        					ApiResponse<List<InStockItemResponse>> apiResponse = new ApiResponse<>();	
                            ApiResponse.Header header = new ApiResponse.Header();
                            header.setRequestId(UUID.randomUUID().toString()); 
                            header.setTimestamp(LocalDateTime.now().toString()); 
                            apiResponse.setHeader(header);
                            
                            ApiResponse.Status status = new ApiResponse.Status();
                            status.setCode(HttpStatus.OK.value());
                            status.setDescription(HttpStatus.OK.getReasonPhrase());
                            apiResponse.setStatus(status);
                            
                            apiResponse.setContent(response);
                            
                            return Mono.just(apiResponse);
        				})
        				.map(ResponseEntity::ok);
                    } else {
                        return Mono.just(new ResponseEntity<>(new LoginResponseGateway(), HttpStatus.OK)); 
                    }
                });
	}

    @GetMapping("in-stock-items/{id}")
	public Mono<ResponseEntity<?>> getInStockItem(
			@RequestHeader("IniBukanKunci") String authorizationHeader,
			@PathVariable String id) {
		return healthCheckService.checkBackendHealth()
                .flatMap(isAvailable -> {
                	if (isAvailable) {
                        return webClientBuilder.build().get().uri(uriBuilder -> uriBuilder
                        		.path(Environment.SERVICE_URL+"instock-item/get-item-by-id")
                        		.queryParam("id", id)
                        		.build())
                				.headers(headers -> headers.setBearerAuth(
        						authorizationHeader))
        				.retrieve()
        				.bodyToMono(new ParameterizedTypeReference<InStockItemResponse>() {})
        				.flatMap(response -> {
        					ApiResponse<InStockItemResponse> apiResponse = new ApiResponse<>();	
                            ApiResponse.Header header = new ApiResponse.Header();
                            header.setRequestId(UUID.randomUUID().toString()); 
                            header.setTimestamp(LocalDateTime.now().toString()); 
                            apiResponse.setHeader(header);
                            
                            ApiResponse.Status status = new ApiResponse.Status();
                            status.setCode(HttpStatus.OK.value());
                            status.setDescription(HttpStatus.OK.getReasonPhrase());
                            apiResponse.setStatus(status);
                            
                            apiResponse.setContent(response);
                            
                            return Mono.just(apiResponse);
        				})
        				.map(ResponseEntity::ok);
                    } else {
                        return Mono.just(new ResponseEntity<>(new LoginResponseGateway(), HttpStatus.OK)); 
                    }
                });
	}
	
//    @PutMapping("in-stock-items/restock")
//    public ResponseEntity<?> getInStockItemById(@PathVariable String id) {
//    	
//    }
//    
//    @PostMapping("in-stock-items")
//    public ResponseEntity<?> getInStockItemById(@PathVariable String id) {
//    	
//    }
//    
//	@GetMapping("warehouse-items")
//	public Mono<ResponseEntity<?>> getWarehouseItem(@RequestHeader("IniBukanKunci") String authorizationHeader) {
//		return healthCheckService.checkBackendHealth()
//                .flatMap(isAvailable -> {
//                	if (isAvailable) {
//                        return webClientBuilder.build().get().uri(Environment.SERVICE_URL+"instock-item/get-all-items")
//                				.headers(headers -> headers.setBearerAuth(
//        						authorizationHeader))
//        				.retrieve()
//        				.bodyToMono(new ParameterizedTypeReference<List<InStockItemResponse>>() {})
//        				.flatMap(response -> {
//        					ApiResponse<List<InStockItemResponse>> apiResponse = new ApiResponse<>();	
//                            ApiResponse.Header header = new ApiResponse.Header();
//                            header.setRequestId(UUID.randomUUID().toString()); 
//                            header.setTimestamp(LocalDateTime.now().toString()); 
//                            apiResponse.setHeader(header);
//                            
//                            ApiResponse.Status status = new ApiResponse.Status();
//                            status.setCode(HttpStatus.OK.value());
//                            status.setDescription(HttpStatus.OK.getReasonPhrase());
//                            apiResponse.setStatus(status);
//                            
//                            apiResponse.setContent(response);
//                            
//                            return Mono.just(apiResponse);
//        				})
//        				.map(ResponseEntity::ok);
//                    } else {
//                        return Mono.just(new ResponseEntity<>(new LoginResponseGateway(), HttpStatus.OK)); 
//                    }
//                });
//	}
//    
//    @GetMapping("warehouse-items/{id}")
//    public ResponseEntity<?> getInStockItemById(@PathVariable String id) {
//    	
//    }
}
