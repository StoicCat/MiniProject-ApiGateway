package com.miniProject.OlShop.service.impl;

import java.util.List;

import com.miniProject.OlShop.entity.PurchaseTransaction;
import com.miniProject.OlShop.model.request.CreatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.request.UpdatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;
import com.miniProject.OlShop.service.PurchaseTransactionService;

import jakarta.transaction.Transactional;

public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

	@Override
	@Transactional
	public void add(CreatePurchaseTransactionRequest request) {
		
	}

	@Override
	@Transactional
	public void edit(UpdatePurchaseTransactionRequest request) {
		
	}

	@Override
	@Transactional
	public void delete(String id) {
		
	}

	@Override
	public PurchaseTransactionResponse getById(String id) {
		
		return null;
	}

	@Override
	public List<PurchaseTransactionResponse> getAll() {
		
		return null;
	}

	@Override
	public PurchaseTransaction getEntityById(String id) {
		
		return null;
	}
	
}
