package com.miniProject.OlShop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreateWarehouseItemRequest;
import com.miniProject.OlShop.model.request.UpdateWarehouseItemRequest;
import com.miniProject.OlShop.model.response.WarehouseItemResponse;
import com.miniProject.OlShop.service.WarehouseItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("warehouse-item")
@RequiredArgsConstructor
@Tag(name = "Warehouse Item Management", description = "APIs for managing warehouse items")
public class WarehouseItemController {
	
	private final WarehouseItemService service;
	
	@PostMapping("create-new-warehouse-item")
	@Operation(summary = "Create a new warehouse item")
	public ResponseEntity<String> createNewWarehouseItem(@RequestBody CreateWarehouseItemRequest request) {
		service.add(request);
		return new ResponseEntity<>("Item has been created", HttpStatus.CREATED);
	}
	
//	@PostMapping("get-all-warehouse-items")
//	@Operation(summary = "Get all warehouse item")
//	public ResponseEntity<String> getAllWarehouseItems() {
//		service.add(request);
//		return new ResponseEntity<>("Item has been created", HttpStatus.CREATED);
//	}
	
	@GetMapping("get-warehouse-item-by-id/{id}")
	@Operation(summary = "Get a warehouse item by ID")
	public ResponseEntity<WarehouseItemResponse> getWarehouseItemById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("edit-warehouse-item")
	@Operation(summary = "Edit an existing warehouse item")
	public ResponseEntity<String> editWarehouseItem(@RequestBody UpdateWarehouseItemRequest request){
		service.edit(request);
		return new ResponseEntity<>("Item successfully edited", HttpStatus.OK);
	}
	
	@DeleteMapping("delete-warehouse-item/{id}")
	@Operation(summary = "Delete a warehouse item by ID")
	public ResponseEntity<String> deleteWarehouseItem(@PathVariable String id) {
		service.delete(id);
		return new ResponseEntity<>("Item successfully deleted", HttpStatus.OK);
	}

}
