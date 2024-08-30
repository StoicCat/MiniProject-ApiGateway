package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreateItemRequestDetailRequest;
import com.miniProject.OlShop.model.response.ItemRequestDetailResponse;
import com.miniProject.OlShop.service.ItemRequestDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item-request-detail")
@RequiredArgsConstructor
@Tag(name = "Item Request Detail Management", description = "APIs for managing item request details")
public class ItemRequestDetailController {
    private final ItemRequestDetailService itemRequestDetailService;

    @PutMapping("add-purchase-transaction-detail")
    @Operation(summary = "Add a new item request detail")
    public ResponseEntity<String> add(@RequestBody CreateItemRequestDetailRequest request) {
        itemRequestDetailService.add(request);
        return new ResponseEntity<>("Add Success", HttpStatus.OK);
    }

    @GetMapping("get-all-purchase-transaction-details-by-purchase-transaction-id/{purchaseTransactionId}")
    @Operation(summary = "Get all item request details by request ID")
    public ResponseEntity<List<ItemRequestDetailResponse>> get(@PathVariable String purchaseTransactionId) {
        return new ResponseEntity<>(itemRequestDetailService.getAllByItemRequestId(purchaseTransactionId), HttpStatus.OK);
    }

    @PostMapping("delete-item-by-id")
    @Operation(summary = "Delete an item request detail by ID")
    public ResponseEntity<String> delete(@RequestParam String id) {
        itemRequestDetailService.delete(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }
}
