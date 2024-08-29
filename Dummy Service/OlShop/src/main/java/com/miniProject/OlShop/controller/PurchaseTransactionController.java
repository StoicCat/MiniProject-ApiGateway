package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;
import com.miniProject.OlShop.service.PurchaseTransactionService;
import com.miniProject.OlShop.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchase-transaction")
@RequiredArgsConstructor
public class PurchaseTransactionController {
	
	private final PurchaseTransactionService service;
	private final AuthenticationManager authManager;
	
	@GetMapping("createNewPurchaseTransaction")
	public String createUser(@RequestBody CreatePurchaseTransactionRequest request){
		service.add(request);
		return "berhasil input";
	}
	
	@GetMapping("getPurchaseTransactionById/{id}")
	public ResponseEntity<PurchaseTransactionResponse> GetPurchaseTransactionById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("getAllPurchaseTransaction")
	public ResponseEntity<List<PurchaseTransactionResponse>> GetAllPurchaseTransaction(){
		final var response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
