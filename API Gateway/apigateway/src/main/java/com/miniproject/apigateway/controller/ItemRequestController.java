package com.miniproject.apigateway.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.miniproject.apigateway.constant.Environment;
import com.miniproject.apigateway.model.request.AccItemRequestRequest;
import com.miniproject.apigateway.model.request.CreateItemRequestDetailRequest;
import com.miniproject.apigateway.model.request.CreateItemRequestRequest;
import com.miniproject.apigateway.model.request.CreateRequestItemRequestGateway;
import com.miniproject.apigateway.model.response.ItemRequestResponse;
import com.miniproject.apigateway.service.HealthCheckService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("item-requests")
@RequiredArgsConstructor
public class ItemRequestController {

    private final HealthCheckService healthCheckService;
    private final WebClient.Builder webClientBuilder;
    
    @PutMapping("acc-item")
    public Mono<ResponseEntity<?>> accRequestItem(@RequestBody AccItemRequestRequest request) {
        return healthCheckService.checkBackendHealth()
            .flatMap(isAvailable -> {
                if (isAvailable) {
                    return webClientBuilder.build()
                        .put()
                        .uri(Environment.SERVICE_URL + "item-request/acc-item-request")
                        .retrieve()
                        .bodyToMono(Map.class)
                        .flatMap(accItemRequestDetailResponse -> 
                            webClientBuilder.build()
                                .get()
                                .uri(Environment.SERVICE_URL + "item-request-detail/get-all-purchase-transaction-details-by-purchase-transaction-id/{itemRequestId}", request.getId())
                                .retrieve()
                                .bodyToMono(ItemRequestResponse.class)
                                .flatMap(itemRequestResponse -> 
                                    webClientBuilder.build()
                                        .post()
                                        .uri(Environment.SERVICE_URL + "warehouse-item/create-new-warehouse-item")
                                        .bodyValue(itemRequestResponse)
                                        .retrieve()
                                        .bodyToMono(Map.class) // Replace with appropriate model if available
                                        .map(response -> ResponseEntity.ok(response))
                                )
                        );
                } else {
                    return Mono.just(ResponseEntity.status(503).body("Backend service is unavailable"));
                }
            });
    }


    @PostMapping("request-item")
    public Mono<ResponseEntity<?>> addRequestItem(@RequestBody CreateRequestItemRequestGateway request) {
        return healthCheckService.checkBackendHealth()
            .flatMap(isAvailable -> {
                if (isAvailable) {
                    CreateItemRequestRequest createItemRequest = new CreateItemRequestRequest();
                    createItemRequest.setUserSupplierId(request.getSupplierItemId());

                    return webClientBuilder.build()
                        .post()
                        .uri(Environment.SERVICE_URL + "item-request/add-item-request")
                        .bodyValue(createItemRequest)
                        .retrieve()
                        .bodyToMono(String.class) 
                        .flatMap(itemRequestId -> {
                            CreateItemRequestDetailRequest createItemRequestDetail = new CreateItemRequestDetailRequest();
                            createItemRequestDetail.setItemRequestId(itemRequestId);
                            createItemRequestDetail.setQty(request.getQty());
                            createItemRequestDetail.setSupplierItemId(request.getSupplierItemId());

                            return webClientBuilder.build()
                                .post()
                                .uri(Environment.SERVICE_URL + "item-request-detail/add-item-request-detail")
                                .bodyValue(createItemRequestDetail)
                                .retrieve()
                                .bodyToMono(Map.class)
                                .map(response -> ResponseEntity.ok(response));
                        });
                } else {
                    return Mono.just(ResponseEntity.status(503).body("Backend service is unavailable"));
                }
            });
    }

}
