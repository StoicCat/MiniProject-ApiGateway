package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreateSupplierItemRequest;
import com.miniProject.OlShop.model.request.UpdateSupplierItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;
import com.miniProject.OlShop.service.SupplierItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("supplier-item")
@RequiredArgsConstructor
@Api(tags = "Supplier Item Management")
public class SupplierItemController {
	
	private final SupplierItemService service;
	
	@PostMapping("create-new-supplier-item")
	@ApiOperation(value = "Create a new supplier item")
	public String createNewSupplierItem(@RequestBody CreateSupplierItemRequest request) {
		service.add(request);
		return "Supplier item added";
	}
	
	@PostMapping("edit-supplier-item")
	@ApiOperation(value = "Edit an existing supplier item")
	public String editSupplierItem(@RequestBody UpdateSupplierItemRequest request) {
		service.edit(request);
		return "Supplier item edited";
	}
	
	@PatchMapping("delete-supplier-item/{id}")
	@ApiOperation(value = "Delete a supplier item by ID")
	public String deleteSupplierItem(@PathVariable String id) {
		service.delete(id);
		return "Supplier item deleted";
	}
	
	@GetMapping("get-supplier-item-by-id/{id}")
	@ApiOperation(value = "Get a supplier item by ID")
	public ResponseEntity<SupplierItemResponse> getSupplierItemById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("get-all-supplier-item")
	@ApiOperation(value = "Get all supplier items")
	public ResponseEntity<List<SupplierItemResponse>> getAllSupplierItem(){
		final var response = service.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
