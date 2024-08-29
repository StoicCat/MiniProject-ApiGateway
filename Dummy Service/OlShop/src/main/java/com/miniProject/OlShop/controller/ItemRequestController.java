package com.miniProject.OlShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item-request")
@RequiredArgsConstructor
public class ItemRequestController {
	private final ItemRequestService itemRequestService;
	
	@PutMapping("add-item-request")
    public ResponseEntity<String> add(@RequestParam CreateItemRequestRequest request) {
		itemRequestService.add(request);
        return new ResponseEntity<>("Add Success ", HttpStatus.OK);
    }

	@GetMapping("edit-item-request")
    public ResponseEntity<String> edit(@RequestBody UpdateItemRequestRequest request) {
		itemRequestService.edit(request);
        return new ResponseEntity<>("Edit Success ", HttpStatus.OK);
    }
    
    @PostMapping("delete-item-request-by-id")
    public ResponseEntity<String> delete(@RequestParam String id) {
    	itemRequestService.delete(id);
        return new ResponseEntity<>("Delete Success ", HttpStatus.OK);
    }
	
    @PutMapping("get-all-item-requests")
    public ResponseEntity<List<ItemRequestResponse>> getAll() {
        return new ResponseEntity<>(itemRequestService.getAll(), HttpStatus.OK);
    }
    
    @PutMapping("get-item-request-by-id")
    public ResponseEntity<ItemRequestResponse> get(@RequestParam String id) {
        return new ResponseEntity<>(itemRequestService.getById(id), HttpStatus.OK);
    }
}
