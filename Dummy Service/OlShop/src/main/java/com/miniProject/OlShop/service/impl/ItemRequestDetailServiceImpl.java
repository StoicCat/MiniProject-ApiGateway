package com.miniProject.OlShop.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.miniProject.OlShop.entity.ItemRequestDetail;
import com.miniProject.OlShop.model.request.CreateItemRequestDetailRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestDetailRequest;
import com.miniProject.OlShop.model.response.ItemRequestDetailResponse;
import com.miniProject.OlShop.repository.ItemRequestDetailRepository;
import com.miniProject.OlShop.service.ItemRequestDetailService;
import com.miniProject.OlShop.service.ItemRequestService;
import com.miniProject.OlShop.service.PrincipalService;
import com.miniProject.OlShop.service.SupplierItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemRequestDetailServiceImpl implements ItemRequestDetailService {

	private final PrincipalService principalService;

	private final ItemRequestService itemRequestService;

	private final SupplierItemService supplierItemService;

	private final ItemRequestDetailRepository repository;

	@Transactional
	@Override
	public void add(CreateItemRequestDetailRequest request) {
		ItemRequestDetail entity = new ItemRequestDetail();
		entity.setQty(request.getQty());
		itemRequestService.getEntityById(request.getItemRequestId()).ifPresent(entity::setItemRequest);
		supplierItemService.getEntityById(request.getSupplierItemId()).ifPresent(entity::setSupplierItem);
		repository.saveAndFlush(entity);
	}

	@Transactional
	@Override
	public void edit(UpdateItemRequestDetailRequest request) {
		getEntityById(request.getId()).ifPresentOrElse(entity -> {
			entity.setQty(request.getQty());
			entity.setUpdatedBy(principalService.getUserId());
			entity.setUpdatedAt(LocalDateTime.now());
			repository.saveAndFlush(entity);
		}, () -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
		});

	}

	@Override
	public ItemRequestDetailResponse getById(String id) {
		ItemRequestDetail entity = getEntityById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Tidak Ditemukan"));

		ItemRequestDetailResponse response = new ItemRequestDetailResponse();

		response.setUpdatedAt(entity.getUpdatedAt());
		response.setVersion(entity.getVer());
		response.setId(id);
		response.setItemRequestId(entity.getItemRequest().getId());
		response.setSupplierItemId(entity.getSupplierItem().getId());
		response.setQty(entity.getQty());

		return response;
	}

	@Override
	public Optional<ItemRequestDetail> getEntityById(String id) {
		return repository.findById(id);
	}

	@Override
	public List<ItemRequestDetailResponse> getAll() {
		List<ItemRequestDetail> itemRequests = repository.findAll();

		List<ItemRequestDetailResponse> responses = new ArrayList<>();

		for (ItemRequestDetail entity : itemRequests) {

			ItemRequestDetailResponse response = new ItemRequestDetailResponse();

			response.setUpdatedAt(entity.getUpdatedAt());
			response.setVersion(entity.getVer());
			response.setId(entity.getId());
			response.setItemRequestId(entity.getItemRequest().getId());
			response.setSupplierItemId(entity.getSupplierItem().getId());
			response.setQty(entity.getQty());

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
