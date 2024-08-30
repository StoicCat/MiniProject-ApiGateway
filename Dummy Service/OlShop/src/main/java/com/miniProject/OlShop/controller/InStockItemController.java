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

import com.miniProject.OlShop.model.request.CreateInStockItemRequest;
import com.miniProject.OlShop.model.request.UpdateInStockItemRequest;
import com.miniProject.OlShop.model.response.InstockItemResponse;
import com.miniProject.OlShop.service.InStockItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("instock-item")
@RequiredArgsConstructor
@Tag(name = "InStock Item Management", description = "APIs for managing in-stock items")
public class InStockItemController {
    private final InStockItemService inStockItemService;

    @PostMapping("add-item")
    @Operation(summary = "Add a new item to stock")
    public ResponseEntity<String> addItem(@RequestBody CreateInStockItemRequest request) {
        inStockItemService.add(request);
        return new ResponseEntity<>("Add Success", HttpStatus.OK);
    }

    @PutMapping("edit-item-quantity")
    @Operation(summary = "Edit the quantity of an existing item")
    public ResponseEntity<String> editItem(@RequestBody UpdateInStockItemRequest request) {
        inStockItemService.edit(request);
        return new ResponseEntity<>("Edit Success", HttpStatus.OK);
    }

    @DeleteMapping("delete-item-by-id")
    @Operation(summary = "Delete an item by its ID")
    public ResponseEntity<String> deleteItem(@RequestParam String id) {
        inStockItemService.delete(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }

    @GetMapping("get-all-items")
    @Operation(summary = "Retrieve all items in stock")
    public ResponseEntity<List<InstockItemResponse>> getItems() {
        return new ResponseEntity<>(inStockItemService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get-item-by-id")
    @Operation(summary = "Retrieve an item by its ID")
    public ResponseEntity<InstockItemResponse> getItems(@RequestParam String id) {
        return new ResponseEntity<>(inStockItemService.getById(id), HttpStatus.OK);
    }
}
