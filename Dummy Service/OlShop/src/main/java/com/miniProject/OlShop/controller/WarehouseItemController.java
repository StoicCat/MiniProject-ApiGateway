package com.miniProject.OlShop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreateWarehouseItemRequest;
import com.miniProject.OlShop.model.request.UpdateWarehouseItemRequest;
import com.miniProject.OlShop.model.response.WarehouseItemResponse;
import com.miniProject.OlShop.service.WarehouseItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("warehouse-item")
@RequiredArgsConstructor
@Api(tags = "Warehouse Item Management")
public class WarehouseItemController {
	
	private final WarehouseItemService service;
	
	@PostMapping("create-new-warehouse-item")
	@ApiOperation(value = "Create a new warehouse item")
	public String CreateNewWarehouseItem(@RequestBody CreateWarehouseItemRequest request) {
		service.add(request);
		return "Item has been created";
	}
	
	@GetMapping("get-warehouse-item-by-id/{id}")
	@ApiOperation(value = "Get a warehouse item by ID")
	public ResponseEntity<WarehouseItemResponse> getWarehouseItemById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("edit-warehouse-item")
	@ApiOperation(value = "Edit an existing warehouse item")
	public String editWarehouseItem(@RequestBody UpdateWarehouseItemRequest request){
		service.edit(request);
		return "Item successfully edited";
	}
	
	@DeleteMapping("delete-warehouse-item/{id}")
	@ApiOperation(value = "Delete a warehouse item by ID")
	public String deleteWarehouseItem(@PathVariable String id) {
		service.delete(id);
		return "Item successfully deleted";
	}

}
