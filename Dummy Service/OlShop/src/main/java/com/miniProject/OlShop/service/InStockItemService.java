package com.miniProject.OlShop.service;

import java.util.List;
import java.util.Optional;

import com.miniProject.OlShop.entity.InStockItem;
import com.miniProject.OlShop.model.request.CreateInStockItemRequest;
import com.miniProject.OlShop.model.request.UpdateInStockItemRequest;
import com.miniProject.OlShop.model.response.InstockItemResponse;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;

public interface InStockItemService {
	void add(CreateInStockItemRequest request);
	void edit(UpdateInStockItemRequest request);
	void delete(String id);
	InstockItemResponse getById(String id);
	List<PurchaseTransactionResponse> getAll();
	Optional<InStockItem> getEntityById(String id);
}
