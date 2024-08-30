package com.miniproject.apigateway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("gateway")
@RequiredArgsConstructor
public class ItemController {

	private final WebClient.Builder webClientBuilder;

	@GetMapping("in-stock-items")
	public Mono<ResponseEntity<?>> getInStockItem(@RequestHeader("IniBukanKunci") String authorizationHeader) {
		return webClientBuilder.build().get().uri("http://localhost:8080/supplier-item/get-all-supplier-item")
				.headers(headers -> headers.setBearerAuth(
						authorizationHeader))
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
				.map(ResponseEntity::ok);
	}

//    @GetMapping("in-stock-items/{id}")
//    public ResponseEntity<?> getInStockItemById(@PathVariable String id) {
//        Map<String, String> filteredCredentials = new HashMap<>();
//    	
//    }
//    
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
//    @GetMapping("warehouse-items")
//    public ResponseEntity<?> getInStockItemById(@PathVariable String id) {
//    	
//    }
//    
//    @GetMapping("warehouse-items/{id}")
//    public ResponseEntity<?> getInStockItemById(@PathVariable String id) {
//    	
//    }
}
