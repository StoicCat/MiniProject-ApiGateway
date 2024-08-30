package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreateItemRequestRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestRequest;
import com.miniProject.OlShop.model.response.ItemRequestResponse;
import com.miniProject.OlShop.service.ItemRequestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item-request")
@RequiredArgsConstructor
@Tag(name = "Item Request Management", description = "APIs for managing item requests")
public class ItemRequestController {
    private final ItemRequestService itemRequestService;

    @PostMapping("add-item-request")
    @Operation(summary = "Add a new item request")
    public ResponseEntity<String> add(@RequestBody CreateItemRequestRequest request) {
        itemRequestService.add(request);
        return new ResponseEntity<>("Add Success", HttpStatus.OK);
    }

    @PutMapping("acc-item-request")
    @Operation(summary = "acc an existing item request")
    public ResponseEntity<String> acc(@RequestBody UpdateItemRequestRequest request) {
        itemRequestService.acc(request);
        return new ResponseEntity<>("acc Success", HttpStatus.OK);
    }
    
    @PutMapping("dcl-item-request")
    @Operation(summary = "decline an existing item request")
    public ResponseEntity<String> dcl(@RequestBody UpdateItemRequestRequest request) {
    	itemRequestService.dcl(request);
    	return new ResponseEntity<>("decline Success", HttpStatus.OK);
    }

    @DeleteMapping("delete-item-request-by-id")
    @Operation(summary = "Delete an item request by ID")
    public ResponseEntity<String> delete(@RequestParam String id) {
        itemRequestService.delete(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }

    @GetMapping("get-all-item-requests")
    @Operation(summary = "Retrieve all item requests")
    public ResponseEntity<List<ItemRequestResponse>> getAll() {
        return new ResponseEntity<>(itemRequestService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get-item-request-by-id")
    @Operation(summary = "Retrieve an item request by ID")
    public ResponseEntity<ItemRequestResponse> get(@RequestParam String id) {
        return new ResponseEntity<>(itemRequestService.getById(id), HttpStatus.OK);
    }
}
