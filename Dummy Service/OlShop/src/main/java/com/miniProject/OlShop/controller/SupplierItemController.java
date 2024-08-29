package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreateSupplierItemRequest;
import com.miniProject.OlShop.model.request.UpdateSupplierItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;
import com.miniProject.OlShop.service.SupplierItemService;
import com.miniProject.OlShop.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("supplier-item")
@RequiredArgsConstructor
public class SupplierItemController {
	
	private final SupplierItemService service;
	
	@GetMapping("create-new-supplier-item")
	public String createNewSupplierItem(@RequestBody CreateSupplierItemRequest request) {
		service.add(request);
		return "supplier item added";
	}
	
	@GetMapping("edit-supplier-item")
	public String editSupplierItem(@RequestBody UpdateSupplierItemRequest request) {
		service.edit(request);
		return "supplier item edited";
	}
	
	@PatchMapping("delete-supplier-item/{id}")
	public String deleteSupplierItem(@PathVariable String id) {
		service.delete(id);
		return "supplier item deleted";
	}
	
	@PostMapping("get-supplier-item-by-id/{id}")
	public ResponseEntity<SupplierItemResponse> getSupplierItemById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("get-all-supplier-item")
	public ResponseEntity<List<SupplierItemResponse>> getAllSupplierItem(){
		final var response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
