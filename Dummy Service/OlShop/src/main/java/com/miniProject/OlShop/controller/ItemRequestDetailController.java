package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreateItemRequestDetailRequest;
import com.miniProject.OlShop.model.response.ItemRequestDetailResponse;
import com.miniProject.OlShop.service.ItemRequestDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item-request-detail")
@RequiredArgsConstructor
@Api(tags = "Item Request Detail Management")
public class ItemRequestDetailController {
	private final ItemRequestDetailService itemRequestDetailService;

	@PutMapping("add-purchase-transaction-detail")
	@ApiOperation(value = "Add a new item request detail")
    public ResponseEntity<String> add(@RequestBody CreateItemRequestDetailRequest request) {
		itemRequestDetailService.add(request);
        return new ResponseEntity<>("Add Success", HttpStatus.OK);
    }

	@GetMapping("get-all-purchase-transaction-details-by-purchase-transaction-id/{purchaseTransactionId}")
	@ApiOperation(value = "Get all item request details by request ID")
    public ResponseEntity<List<ItemRequestDetailResponse>> get(@PathVariable String purchaseTransactionId) {
        return new ResponseEntity<>(itemRequestDetailService.getAllByItemRequestId(purchaseTransactionId), HttpStatus.OK);
    }
    
    @PostMapping("delete-item-by-id")
	@ApiOperation(value = "Delete an item request detail by ID")
    public ResponseEntity<String> delete(@RequestParam String id) {
    	itemRequestDetailService.delete(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }
}
