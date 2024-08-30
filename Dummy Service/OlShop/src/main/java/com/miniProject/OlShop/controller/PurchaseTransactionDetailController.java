package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionDetailResponse;
import com.miniProject.OlShop.service.PurchaseTransactionDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchase-transaction-detail")
@RequiredArgsConstructor
@Api(tags = "Purchase Transaction Detail Management")
public class PurchaseTransactionDetailController {
	private final PurchaseTransactionDetailService purchaseTransactionDetailService;
	
	@PutMapping("add-purchase-transaction-detail")
	@ApiOperation(value = "Add a new purchase transaction detail")
    public ResponseEntity<String> add(@RequestBody CreatePurchaseTransactionDetailRequest request) {
		purchaseTransactionDetailService.add(request);
        return new ResponseEntity<>("Add Success", HttpStatus.OK);
    }

	@GetMapping("get-all-purchase-transaction-details-by-purchase-transaction-id/{purchaseTransactionId}")
	@ApiOperation(value = "Get all purchase transaction details by transaction ID")
    public ResponseEntity<List<PurchaseTransactionDetailResponse>> get(@PathVariable String purchaseTransactionId) {
        return new ResponseEntity<>(purchaseTransactionDetailService.getAllByPurchaseTransactionId(purchaseTransactionId), HttpStatus.OK);
    }
}
