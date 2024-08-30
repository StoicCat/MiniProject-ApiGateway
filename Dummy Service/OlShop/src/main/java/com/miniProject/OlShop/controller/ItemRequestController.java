package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("add-item-request")
    @Operation(summary = "Add a new item request")
    public ResponseEntity<String> add(@RequestBody CreateItemRequestRequest request) {
        itemRequestService.add(request);
        return new ResponseEntity<>("Add Success", HttpStatus.OK);
    }

    @PutMapping("edit-item-request")
    @Operation(summary = "Edit an existing item request")
    public ResponseEntity<String> edit(@RequestBody UpdateItemRequestRequest request) {
        itemRequestService.edit(request);
        return new ResponseEntity<>("Edit Success", HttpStatus.OK);
    }

    @PostMapping("delete-item-request-by-id")
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
