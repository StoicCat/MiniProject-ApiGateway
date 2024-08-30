package com.miniProject.OlShop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.miniProject.OlShop.constant.AcceptanceStatus;
import com.miniProject.OlShop.entity.ItemRequest;
import com.miniProject.OlShop.model.request.CreateItemRequestRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestRequest;
import com.miniProject.OlShop.model.response.ItemRequestResponse;
import com.miniProject.OlShop.repository.ItemRequestRepository;
import com.miniProject.OlShop.service.ItemRequestService;
import com.miniProject.OlShop.service.PrincipalService;
import com.miniProject.OlShop.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {

	private final PrincipalService principalService;

	private final UserService userService;

	private final ItemRequestRepository repository;

	@Transactional
	@Override
	public void add(CreateItemRequestRequest request) {
		ItemRequest entity = new ItemRequest();
		entity.setAcceptanceStatus(AcceptanceStatus.PND.getCode());
		userService.getEntityById(request.getUserSupplierId()).ifPresent(entity::setUserSupplier);
		repository.saveAndFlush(entity);
	}

	@Transactional
	@Override
	public void acc(UpdateItemRequestRequest request) {
		getEntityById(request.getId()).ifPresentOrElse(entity -> {
			entity.setAcceptanceStatus(AcceptanceStatus.ACC.getCode());
			entity.setUpdatedBy(principalService.getUserId());
			repository.saveAndFlush(entity);
		}, () -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
		});

	}
	
	@Transactional
	@Override
	public void dcl(UpdateItemRequestRequest request) {
		getEntityById(request.getId()).ifPresentOrElse(entity -> {
			entity.setAcceptanceStatus(AcceptanceStatus.DCL.getCode());
			entity.setUpdatedBy(principalService.getUserId());
			repository.saveAndFlush(entity);
		}, () -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
		});
		
	}

	@Override
	public ItemRequestResponse getById(String id) {
		ItemRequest entity = getEntityById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Tidak Ditemukan"));

		ItemRequestResponse response = new ItemRequestResponse();

		response.setUpdatedAt(entity.getUpdatedAt());
		response.setVersion(entity.getVer());
		response.setId(id);
		response.setAcceptanceStatus(entity.getAcceptanceStatus());
		response.setUserSupplierId(entity.getUserSupplier().getId());
		return response;
	}

	@Override
	public Optional<ItemRequest> getEntityById(String id) {
		return repository.findById(id);
	}

	@Override
	public List<ItemRequestResponse> getAll() {
		List<ItemRequest> itemRequests = repository.findAll();

		List<ItemRequestResponse> responses = new ArrayList<>();

		for (ItemRequest entity : itemRequests) {

			ItemRequestResponse response = new ItemRequestResponse();

			response.setUpdatedAt(entity.getUpdatedAt());
			response.setVersion(entity.getVer());
			response.setId(entity.getId());
			response.setAcceptanceStatus(entity.getAcceptanceStatus());
			response.setUserSupplierId(entity.getUserSupplier().getId());

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
