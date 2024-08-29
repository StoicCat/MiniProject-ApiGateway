package com.miniProject.OlShop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreateWarehouseItemRequest;
import com.miniProject.OlShop.model.request.UpdateWarehouseItemRequest;
import com.miniProject.OlShop.model.response.WarehouseItemResponse;
import com.miniProject.OlShop.service.UserService;
import com.miniProject.OlShop.service.WarehouseItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("warehouse-item")
@RequiredArgsConstructor
public class WarehouseItemController {
	
	private final WarehouseItemService service;
	
	@GetMapping("create-new-warehouse-item")
	public String CreateNewWarehouseItem(@RequestBody CreateWarehouseItemRequest request) {
		service.add(request);
		return "item has been created";
	}
	
	@PostMapping("get-warehouse-item-by-id/{id}")
	public ResponseEntity<WarehouseItemResponse> getWarehouseItemById(@PathVariable String id){
		final var response = service.getById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("edit-warehouse-item")
	public String editWarehouseItem(@RequestBody UpdateWarehouseItemRequest request){
		service.edit(request);
		return "item successfully edited";
	}
	
	@PatchMapping("delete-warehouse-item/{id}")
	public String deleteWarehouseItem(@PathVariable String id) {
		service.delete(id);
		return "item successfully deleted";
	}

}
