package com.miniProject.OlShop.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.miniProject.OlShop.entity.PurchaseTransaction;
import com.miniProject.OlShop.helper.CodeGenerationHelper;
import com.miniProject.OlShop.model.request.CreatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.request.UpdatePurchaseTransactionRequest;
import com.miniProject.OlShop.model.response.PurchaseTransactionResponse;
import com.miniProject.OlShop.repository.PurchaseTransactionRepository;
import com.miniProject.OlShop.service.PrincipalService;
import com.miniProject.OlShop.service.PurchaseTransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

	private final PrincipalService principalService;
	private final PurchaseTransactionRepository repository;
	private final CodeGenerationHelper codeGenerator;
	private static final String MSG_PURCHASE_TRANSACTION = "purchase transaction";

	@Override
	@Transactional
	public void add(CreatePurchaseTransactionRequest request) {
		PurchaseTransaction entity = mapToEntity(request);
		repository.saveAndFlush(entity);
	}

	@Override
	@Transactional
	public void edit(UpdatePurchaseTransactionRequest request) {
		PurchaseTransaction entity = getEntityById(request.getId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_PURCHASE_TRANSACTION + "is not exist"));
		repository.saveAndFlush(entity);
	}

	@Override
	@Transactional
	public void delete(String id) {
		repository.findById(id).ifPresentOrElse(entity -> repository.delete(entity), () -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
		});
	}

	@Override
	public PurchaseTransactionResponse getById(String id) {
		PurchaseTransaction entity = getEntityById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_PURCHASE_TRANSACTION + "is not exist"));

		return mapToResponse(entity);
	}

	@Override
	public List<PurchaseTransactionResponse> getAll() {
		List<PurchaseTransaction> entities = repository.findAll();
		return entities.stream().map(this::mapToResponse).toList();
	}

	@Override
	public Optional<PurchaseTransaction> getEntityById(String id) {

		return repository.findById(id);
	}

	private PurchaseTransaction mapToEntity(CreatePurchaseTransactionRequest request) {
		PurchaseTransaction entity = new PurchaseTransaction();

		entity.setCreatedBy(principalService.getUserId());
		entity.setTransactionCode(codeGenerator.generateCode());
		entity.setTransactionDate(LocalDateTime.now());

		return entity;
	}

	private PurchaseTransactionResponse mapToResponse(PurchaseTransaction entity) {
		PurchaseTransactionResponse response = new PurchaseTransactionResponse();
		response.setId(entity.getId());
		response.setTransactionCode(entity.getTransactionCode());
		response.setTransactionDate(entity.getTransactionDate());
		response.setUserFullName(entity.getUser().getFullName());
		response.setUserId(entity.getUser().getId());
		response.setUserRole(entity.getUser().getRole());

		return response;

	}

}
