package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionDetailResponse;
import com.miniProject.OlShop.service.PurchaseTransactionDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchase-transaction-detail")
@RequiredArgsConstructor
@Tag(name = "Purchase Transaction Detail Management", description = "APIs for managing purchase transaction details")
public class PurchaseTransactionDetailController {
	private final PurchaseTransactionDetailService purchaseTransactionDetailService;
	
	@PutMapping("add-purchase-transaction-detail")
	@Operation(summary = "Add a new purchase transaction detail")
    public ResponseEntity<String> add(@RequestBody CreatePurchaseTransactionDetailRequest request) {
		purchaseTransactionDetailService.add(request);
        return new ResponseEntity<>("Add Success", HttpStatus.CREATED);
    }

	@GetMapping("get-all-purchase-transaction-details-by-purchase-transaction-id/{purchaseTransactionId}")
	@Operation(summary = "Get all purchase transaction details by transaction ID")
    public ResponseEntity<List<PurchaseTransactionDetailResponse>> get(@PathVariable String purchaseTransactionId) {
        List<PurchaseTransactionDetailResponse> response = purchaseTransactionDetailService.getAllByPurchaseTransactionId(purchaseTransactionId);
        if (response != null && !response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
