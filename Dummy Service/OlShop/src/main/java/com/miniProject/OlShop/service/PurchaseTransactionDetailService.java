package com.miniProject.OlShop.service;

import java.util.List;
import java.util.Optional;

import com.miniProject.OlShop.entity.PurchaseTransactionDetail;
import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.request.UpdatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionDetailResponse;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;

public interface PurchaseTransactionDetailService {
	void add(CreatePurchaseTransactionDetailRequest request);
	void edit(UpdatePurchaseTransactionDetailRequest request);
	void delete(String id);
	PurchaseTransactionResponse getById(String id);
	List<PurchaseTransactionDetailResponse> getAll();
	Optional<PurchaseTransactionDetail> getEntityById(String id);
}
