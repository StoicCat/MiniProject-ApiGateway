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
import com.miniproject.apigateway.model.request.CreateInStockItemRequest;
import com.miniproject.apigateway.model.request.CreateSupplierItemRequest;
import com.miniproject.apigateway.model.request.CreateUserRequest;
import com.miniproject.apigateway.model.request.UpdateWarehouseItemRequest;
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
	public Mono<ApiResponse<?>> getSupplierItems(@RequestHeader("IniBukanKunci") String authorizationHeader) {
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
        				});
                    } else {
        				return healthCheckService.getErrorMessage(SupplierItemResponse.class);
                    }
                });
	}
	
	@GetMapping("supplier-items/{id}")
	public Mono<ApiResponse<?>> getSupplierItem(
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
        				});
                    } else {
        				return healthCheckService.getErrorMessage(SupplierItemResponse.class);
                    }
                });
	}
	
	@PostMapping("supplier-items")
	public Mono<ApiResponse<String>> addSupplierItems(
			@RequestHeader("IniBukanKunci") String authorizationHeader,
			@RequestBody CreateSupplierItemRequest request) {
		return healthCheckService.checkBackendHealth()
				.flatMap(isAvailable -> {
					if(isAvailable) {
						return webClientBuilder.build()
								.post()
								.uri(Environment.SERVICE_URL+"supplier-item/create-new-supplier-item")
								.header("IniBukanKunci", authorizationHeader) 
								.bodyValue(request)
								.retrieve()
								.bodyToMono(String.class)
								.flatMap(response -> {
		        					ApiResponse<String> apiResponse = new ApiResponse<>();	
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
								});
					}else {
						return healthCheckService.getErrorMessage(String.class);
					}
				});
	}
	
	@GetMapping("in-stock-items")
	public Mono<ApiResponse<?>> getInStockItems(@RequestHeader("IniBukanKunci") String authorizationHeader) {
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
        				});
                    } else {
        				return healthCheckService.getErrorMessage(InStockItemResponse.class);
                    }
                });
	}

    @GetMapping("in-stock-items/{id}")
	public Mono<ApiResponse<?>> getInStockItem(
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
        				});
                    } else {
        				return healthCheckService.getErrorMessage(InStockItemResponse.class);
                    }
                });
	}
    
    @PostMapping("in-stock-items")
    public Mono<ApiResponse<String>> addInstockItem(
			@RequestHeader("IniBukanKunci") String authorizationHeader,
			@RequestBody CreateInStockItemRequest request) {
    	UpdateWarehouseItemRequest warehouseRequest = new UpdateWarehouseItemRequest();
    	warehouseRequest.setQty(request.getQuantity());
    	warehouseRequest.setSupplierItemId(request.getSupplierItemId());
		return healthCheckService.checkBackendHealth()
				.flatMap(isAvailable -> {
					if(isAvailable) {
						return webClientBuilder.build()
								.patch()
								.uri(Environment.SERVICE_URL+"warehouse-item/edit-warehouse-item")
								.header("IniBukanKunci", authorizationHeader) 
								.bodyValue(warehouseRequest)
								.retrieve()
								.bodyToMono(String.class)
								.flatMap(response -> {
									return webClientBuilder.build()
											.post()
											.uri(Environment.SERVICE_URL+"instock-item/add-item")
											.header("IniBukanKunci", authorizationHeader) 
											.bodyValue(request)
											.retrieve()
											.bodyToMono(String.class)
											.flatMap(secondResponse -> {
	                                            ApiResponse<String> apiResponse = new ApiResponse<>();
	                                            ApiResponse.Header header = new ApiResponse.Header();
	                                            header.setRequestId(UUID.randomUUID().toString());
	                                            header.setTimestamp(LocalDateTime.now().toString());
	                                            apiResponse.setHeader(header);
	                                            
	                                            ApiResponse.Status status = new ApiResponse.Status();
	                                            status.setCode(HttpStatus.OK.value());
	                                            status.setDescription(HttpStatus.OK.getReasonPhrase());
	                                            apiResponse.setStatus(status);
	                                            
	                                            apiResponse.setContent(secondResponse);
	                                            
	                                            return Mono.just(apiResponse);
											});
								});
					}else {
						return healthCheckService.getErrorMessage(String.class);
					}
				});
    }
    
//    @PutMapping("in-stock-items/restock")
//    public Mono<ApiResponse<String>> restockInStockItem(
//			@RequestHeader("IniBukanKunci") String authorizationHeader,
//			@RequestBody CreateSupplierItemRequest request) {
//		return healthCheckService.checkBackendHealth()
//				.flatMap(isAvailable -> {
//					if(isAvailable) {
//						return webClientBuilder.build()
//								.post()
//								.uri(Environment.SERVICE_URL+"supplier-item/create-new-supplier-item")
//								.header("IniBukanKunci", authorizationHeader) 
//								.bodyValue(request)
//								.retrieve()
//								.bodyToMono(String.class)
//								.flatMap(response -> {
//		        					ApiResponse<String> apiResponse = new ApiResponse<>();	
//		                            ApiResponse.Header header = new ApiResponse.Header();
//		                            header.setRequestId(UUID.randomUUID().toString()); 
//		                            header.setTimestamp(LocalDateTime.now().toString()); 
//		                            apiResponse.setHeader(header);
//		                            
//		                            ApiResponse.Status status = new ApiResponse.Status();
//		                            status.setCode(HttpStatus.OK.value());
//		                            status.setDescription(HttpStatus.OK.getReasonPhrase());
//		                            apiResponse.setStatus(status);
//		                            
//		                            apiResponse.setContent(response);
//		                            
//		                            return Mono.just(apiResponse);
//								});
//					}else {
//						return healthCheckService.getErrorMessage(String.class);
//					}
//				});
//    }
}
