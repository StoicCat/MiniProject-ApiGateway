package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;
import com.miniProject.OlShop.service.PurchaseTransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchase-transaction")
@RequiredArgsConstructor
@Api(tags = "Purchase Transaction Management")
public class PurchaseTransactionController {
	
	private final PurchaseTransactionService service;

	@PostMapping("createNewPurchaseTransaction")
	@ApiOperation(value = "Create a new purchase transaction")
	public String createPurchaseTransaction(@RequestBody CreatePurchaseTransactionRequest request){
		service.add(request);
		return "Purchase transaction created successfully";
	}
	
	@GetMapping("getPurchaseTransactionById/{id}")
	@ApiOperation(value = "Get a purchase transaction by ID")
	public ResponseEntity<PurchaseTransactionResponse> getPurchaseTransactionById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("getAllPurchaseTransaction")
	@ApiOperation(value = "Get all purchase transactions")
	public ResponseEntity<List<PurchaseTransactionResponse>> getAllPurchaseTransactions(){
		final var response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
