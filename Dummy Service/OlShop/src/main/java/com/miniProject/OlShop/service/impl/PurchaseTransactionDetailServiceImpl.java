package com.miniProject.OlShop.service.impl;

import java.util.List;
import java.util.Optional;

import com.miniProject.OlShop.entity.PurchaseTransactionDetail;
import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.request.UpdatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionDetailResponse;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;
import com.miniProject.OlShop.service.PurchaseTransactionDetailService;

import jakarta.transaction.Transactional;

public class PurchaseTransactionDetailServiceImpl implements PurchaseTransactionDetailService {

	@Override
	@Transactional
	public void add(CreatePurchaseTransactionDetailRequest request) {
		
	}

	@Override
	@Transactional
	public void edit(UpdatePurchaseTransactionDetailRequest request) {
		
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
	public List<PurchaseTransactionDetailResponse> getAll() {
		
		return null;
	}

	@Override
	public Optional<PurchaseTransactionDetail> getEntityById(String id) {
		
		return Optional.empty();
	}

}
