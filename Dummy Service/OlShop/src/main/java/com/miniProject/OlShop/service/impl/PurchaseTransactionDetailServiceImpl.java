package com.miniProject.OlShop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.miniProject.OlShop.entity.PurchaseTransactionDetail;
import com.miniProject.OlShop.entity.SupplierItem;
import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionDetailResponse;
import com.miniProject.OlShop.repository.PurchaseTransactionDetailRepository;
import com.miniProject.OlShop.service.PrincipalService;
import com.miniProject.OlShop.service.PurchaseTransactionDetailService;
import com.miniProject.OlShop.service.PurchaseTransactionService;
import com.miniProject.OlShop.service.SupplierItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class PurchaseTransactionDetailServiceImpl implements PurchaseTransactionDetailService {
	private final PrincipalService principalService;
	private final SupplierItemService supplierItemService;
	@Setter(onMethod_ = @Autowired, onParam_ = @Lazy)
	private PurchaseTransactionService purchaseTransactionService;
	private final PurchaseTransactionDetailRepository repository;

	@Override
	@Transactional
	public void add(CreatePurchaseTransactionDetailRequest request) {
		PurchaseTransactionDetail entity = mapToEntity(request);
		repository.saveAndFlush(entity);
	}

	@Override
	public List<PurchaseTransactionDetail> getAllEntitygetByPurchaseTransactionId(String purchaseTransactionId) {
		return repository.findAllByPurchaseTransactionId(purchaseTransactionId);
	}

	@Override
	public List<PurchaseTransactionDetailResponse> getAllByPurchaseTransactionId(String purchaseTransactionId) {
		return getAllEntitygetByPurchaseTransactionId(purchaseTransactionId).stream().map(this::mapToResponse).toList();
	}

	private PurchaseTransactionDetailResponse mapToResponse(PurchaseTransactionDetail entity) {
		PurchaseTransactionDetailResponse response = new PurchaseTransactionDetailResponse();
		response.setId(entity.getId());
		response.setPurchaseTransactionId(entity.getPurchaseTransaction().getId());
		response.setQuantity(entity.getQty());
		response.setSupplierItemId(entity.getSupplierItem().getId());
		response.setTotalPrice(entity.getTotalPrice());

		return response;
	}

	private PurchaseTransactionDetail mapToEntity(CreatePurchaseTransactionDetailRequest request) {
		PurchaseTransactionDetail entity = new PurchaseTransactionDetail();

		SupplierItem suplierItem = supplierItemService.getEntityById(request.getSupplierItemId()).orElse(null);
		entity.setCreatedBy(principalService.getUserId());
		entity.setQty(request.getQuantity());
		entity.setSupplierItem(suplierItem);
		entity.setPurchaseTransaction(
				purchaseTransactionService.getEntityById(request.getPurchaseTransactionId()).orElse(null));
		entity.setTotalPrice(BigDecimal.valueOf(request.getQuantity()).multiply(suplierItem.getPrice()));

		return entity;
	}
}
