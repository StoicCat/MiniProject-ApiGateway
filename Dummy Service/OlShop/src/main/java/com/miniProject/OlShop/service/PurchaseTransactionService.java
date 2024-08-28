package com.miniProject.OlShop.service;

import java.util.List;

import com.miniProject.OlShop.entity.PurchaseTransaction;
import com.miniProject.OlShop.model.request.CreatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.request.UpdatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;

public interface PurchaseTransactionService {
	void add(CreatePurchaseTransactionRequest request);
	void edit(UpdatePurchaseTransactionRequest request);
	void delete(String id);
	PurchaseTransactionResponse getById(String id);
	List<PurchaseTransactionResponse> getAll();
	PurchaseTransaction getEntityById(String id);
}
