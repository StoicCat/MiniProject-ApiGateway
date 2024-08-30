package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;
import com.miniProject.OlShop.service.PurchaseTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchase-transaction")
@RequiredArgsConstructor
@Tag(name = "Purchase Transaction Management", description = "APIs for managing purchase transactions")
public class PurchaseTransactionController {
	
	private final PurchaseTransactionService service;

	@PostMapping("createNewPurchaseTransaction")
	@Operation(summary = "Create a new purchase transaction")
	public ResponseEntity<String> createPurchaseTransaction(@RequestBody CreatePurchaseTransactionRequest request) {
		service.add(request);
		return new ResponseEntity<>("Purchase transaction created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("getPurchaseTransactionById/{id}")
	@Operation(summary = "Get a purchase transaction by ID")
	public ResponseEntity<PurchaseTransactionResponse> getPurchaseTransactionById(@PathVariable String id) {
		final var response = service.getById(id);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getAllPurchaseTransaction")
	@Operation(summary = "Get all purchase transactions")
	public ResponseEntity<List<PurchaseTransactionResponse>> getAllPurchaseTransactions() {
		final var response = service.getAll();
		if (response != null && !response.isEmpty()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
