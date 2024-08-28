package com.miniProject.OlShop.service;

import java.util.List;

import com.miniProject.OlShop.entity.PurchaseTransactionDetail;
import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionDetailResponse;

public interface PurchaseTransactionDetailService {
	void add(CreatePurchaseTransactionDetailRequest request);
	List<PurchaseTransactionDetail> getAllEntitygetByPurchaseTransactionId(String purchaseTransactionId);
	List<PurchaseTransactionDetailResponse> getAllByPurchaseTransactionId(String purchaseTransactionId);
}
