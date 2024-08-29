package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreateItemRequestDetailRequest;
import com.miniProject.OlShop.model.response.ItemRequestDetailResponse;
import com.miniProject.OlShop.service.ItemRequestDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item-request-detail")
@RequiredArgsConstructor
public class ItemRequestDetailController {
	private final ItemRequestDetailService itemRequestDetailService;

	@PutMapping("add-purchase-transaction-detail")
    public ResponseEntity<String> add(@RequestParam CreateItemRequestDetailRequest request) {
		itemRequestDetailService.add(request);
        return new ResponseEntity<>("Add Success ", HttpStatus.OK);
    }

	@DeleteMapping("get-all-purchase-transaction-details-by-purchase-transaction-id/{purchaseTransactionId}")
    public ResponseEntity<List<ItemRequestDetailResponse>> get(@PathVariable String itemRequestId) {
        return new ResponseEntity<>(itemRequestDetailService.getAllByItemRequestId(itemRequestId), HttpStatus.OK);
    }
    
    @PostMapping("delete-item-by-id")
    public ResponseEntity<String> delete(@RequestParam String id) {
    	itemRequestDetailService.delete(id);
        return new ResponseEntity<>("Delete Success ", HttpStatus.OK);
    }
}
