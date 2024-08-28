package com.miniProject.OlShop.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.miniProject.OlShop.entity.SupplierItem;
import com.miniProject.OlShop.model.request.CreateSupplierItemRequest;
import com.miniProject.OlShop.model.request.UpdateSupplierItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;
import com.miniProject.OlShop.repository.SupplierItemRepository;
import com.miniProject.OlShop.service.PrincipalService;
import com.miniProject.OlShop.service.SupplierItemService;
import com.miniProject.OlShop.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierItemServiceImpl implements SupplierItemService {

	private final PrincipalService principalService;

	private final SupplierItemRepository repository;

	private final UserService userService;

	@Transactional
	@Override
	public void add(CreateSupplierItemRequest request) {
		SupplierItem entity = new SupplierItem();
		entity.setName(request.getName());
		entity.setPrice(request.getPrice());
		userService.getEntityById(request.getUserId()).ifPresent(entity::setUser);
		repository.saveAndFlush(entity);
	}

	@Transactional
	@Override
	public void edit(UpdateSupplierItemRequest request) {
		getEntityById(request.getId()).ifPresentOrElse(entity -> {
			entity.setName(request.getName());
			entity.setPrice(request.getPrice());
			entity.setUpdatedBy(principalService.getUserId());
			entity.setUpdatedAt(LocalDateTime.now());
			repository.saveAndFlush(entity);
			repository.saveAndFlush(entity);
		}, () -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
		});

	}

	@Override
	public SupplierItemResponse getById(String id) {
		SupplierItem entity = getEntityById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Tidak Ditemukan"));

		SupplierItemResponse response = new SupplierItemResponse();

		response.setUpdatedAt(entity.getUpdatedAt());
		response.setVersion(entity.getVer());
		response.setId(id);
		response.setName(entity.getName());
		response.setPrice(entity.getPrice());
		response.setUserId(entity.getUser().getId());

		return response;
	}

	@Override
	public Optional<SupplierItem> getEntityById(String id) {
		return repository.findById(id);
	}

	@Override
	public List<SupplierItemResponse> getAll() {
		List<SupplierItem> supplierItems = repository.findAll();

		List<SupplierItemResponse> responses = new ArrayList<>();

		for (SupplierItem entity : supplierItems) {

			SupplierItemResponse response = new SupplierItemResponse();

			response.setUpdatedAt(entity.getUpdatedAt());
			response.setVersion(entity.getVer());
			response.setId(entity.getId());
			response.setName(entity.getName());
			response.setPrice(entity.getPrice());
			response.setUserId(entity.getUser().getId());

			responses.add(response);
		}

		return responses;
	}

	@Transactional
	@Override
	public void delete(String id) {
		repository.findById(id).ifPresentOrElse(entity -> repository.delete(entity), () -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
		});

	}

}
