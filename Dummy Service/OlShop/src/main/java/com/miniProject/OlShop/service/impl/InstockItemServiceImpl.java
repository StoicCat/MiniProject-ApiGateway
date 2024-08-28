package com.miniProject.OlShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.miniProject.OlShop.entity.InStockItem;
import com.miniProject.OlShop.model.request.CreateInStockItemRequest;
import com.miniProject.OlShop.model.request.UpdateInStockItemRequest;
import com.miniProject.OlShop.model.response.InstockItemResponse;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;
import com.miniProject.OlShop.repository.InStockItemRepository;
import com.miniProject.OlShop.service.InStockItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstockItemServiceImpl implements InStockItemService {
	private final InStockItemRepository repository;
	private static final String MSG_IN_STOCK_ITEM = "in stock item ";
	
	@Override
	@Transactional
	public void add(CreateInStockItemRequest request) {
		InStockItem entity = new InStockItem();
	}

	@Override
	@Transactional
	public void edit(UpdateInStockItemRequest request) {
		InStockItem entity = new InStockItem();
	}

	@Override
	@Transactional
	public void delete(String id) {
		InStockItem entity = new InStockItem();
	}

	@Override
	public InstockItemResponse getById(String id) {
		InStockItem entity = getEntityById(id).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_IN_STOCK_ITEM+"is not exist"));
		
		return mapToResponse(entity);
	}

	@Override
	public List<InstockItemResponse> getAll() {
		List<InStockItem> entities = repository.findAll();
		return entities.stream().map(this::mapToResponse).toList();
	}

	@Override
	public Optional<InStockItem> getEntityById(String id) {
		return repository.findById(id);
	}
	
	private InstockItemResponse mapToResponse(InStockItem entity) {
		InstockItemResponse response = new InstockItemResponse();
		response.setId(entity.getId());
		response.setQuantity(entity.getQty());
		response.setSupplierItemId(entity.getSupplierItem().getId());
		response.setSupplierItemName(entity.getSupplierItem().getName());
		response.setSupplierItemPrice(entity.getSupplierItem().getPrice());
		
		return response;
	}
	
	private InStockItem mapToEntity(CreateInStockItemRequest request) {
		InStockItem entity = new InStockItem();
		
		
		return entity;
	}
}
