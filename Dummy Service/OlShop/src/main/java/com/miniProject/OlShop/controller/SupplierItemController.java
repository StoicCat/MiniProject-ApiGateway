package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreateSupplierItemRequest;
import com.miniProject.OlShop.model.request.UpdateSupplierItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;
import com.miniProject.OlShop.service.SupplierItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("supplier-item")
@RequiredArgsConstructor
@Tag(name = "Supplier Item Management", description = "APIs for managing supplier items")
public class SupplierItemController {
	
	private final SupplierItemService service;
	
	@PostMapping("create-new-supplier-item")
	@Operation(summary = "Create a new supplier item")
	public ResponseEntity<String> createNewSupplierItem(@RequestBody CreateSupplierItemRequest request) {
		service.add(request);
		return new ResponseEntity<>("Supplier item added", HttpStatus.CREATED);
	}
	
	@PutMapping("edit-supplier-item")
	@Operation(summary = "Edit an existing supplier item")
	public ResponseEntity<String> editSupplierItem(@RequestBody UpdateSupplierItemRequest request) {
		service.edit(request);
		return new ResponseEntity<>("Supplier item edited", HttpStatus.OK);
	}
	
	@DeleteMapping("delete-supplier-item/{id}")
	@Operation(summary = "Delete a supplier item by ID")
	public ResponseEntity<String> deleteSupplierItem(@PathVariable String id) {
		service.delete(id);
		return new ResponseEntity<>("Supplier item deleted", HttpStatus.OK);
	}
	
	@GetMapping("get-supplier-item-by-id/{id}")
	@Operation(summary = "Get a supplier item by ID")
	public ResponseEntity<SupplierItemResponse> getSupplierItemById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("get-all-supplier-item")
	@Operation(summary = "Get all supplier items")
	public ResponseEntity<List<SupplierItemResponse>> getAllSupplierItems(){
		final var response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
