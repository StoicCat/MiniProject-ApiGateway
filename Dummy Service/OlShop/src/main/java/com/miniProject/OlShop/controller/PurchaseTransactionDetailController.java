package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionDetailResponse;
import com.miniProject.OlShop.service.PurchaseTransactionDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchase-transaction-detail")
@RequiredArgsConstructor
public class PurchaseTransactionDetailController {
	private final PurchaseTransactionDetailService purchaseTransactionDetailService;
	
	@PutMapping("add-purchase-transaction-detail")
    public ResponseEntity<String> add(@RequestParam CreatePurchaseTransactionDetailRequest request) {
		purchaseTransactionDetailService.add(request);
        return new ResponseEntity<>("Add Success ", HttpStatus.OK);
    }

	@DeleteMapping("get-all-purchase-transaction-details-by-purchase-transaction-id/{purchaseTransactionId}")
    public ResponseEntity<List<PurchaseTransactionDetailResponse>> get(@PathVariable String purchaseTransactionId) {
        return new ResponseEntity<>(purchaseTransactionDetailService.getAllByPurchaseTransactionId(purchaseTransactionId), HttpStatus.OK);
    }
}
