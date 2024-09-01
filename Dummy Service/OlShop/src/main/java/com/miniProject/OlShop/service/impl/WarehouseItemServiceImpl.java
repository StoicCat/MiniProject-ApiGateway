package com.miniProject.OlShop.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.miniProject.OlShop.entity.SupplierItem;
import com.miniProject.OlShop.entity.WarehouseItem;
import com.miniProject.OlShop.model.request.CreateWarehouseItemRequest;
import com.miniProject.OlShop.model.request.UpdateWarehouseItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;
import com.miniProject.OlShop.model.response.WarehouseItemResponse;
import com.miniProject.OlShop.repository.WarehouseItemRepository;
import com.miniProject.OlShop.service.PrincipalService;
import com.miniProject.OlShop.service.SupplierItemService;
import com.miniProject.OlShop.service.WarehouseItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WarehouseItemServiceImpl implements WarehouseItemService {
	
  private final PrincipalService principalService;

  private final SupplierItemService supplierItemService;

  private final WarehouseItemRepository repository;

  @Transactional
  @Override
  public void add(CreateWarehouseItemRequest request) {
    WarehouseItem entity = new WarehouseItem();
    entity.setCreatedAt(LocalDateTime.now());
    entity.setQty(request.getQty());
    supplierItemService.getEntityById(request.getSupplierItemId()).ifPresent(
        entity::setSupplierItem);
    entity.setCreatedBy(principalService.getUserId());
    repository.saveAndFlush(entity);
  
  }

  @Transactional
  @Override
  public void edit(UpdateWarehouseItemRequest request) {

    getEntityBySupplierItemId(request.getSupplierItemId()).ifPresentOrElse(entity -> {
      entity.setQty(request.getQty());
      entity.setUpdatedAt(LocalDateTime.now());
    	entity.setUpdatedBy(principalService.getUserId());
	  	entity.setUpdatedAt(LocalDateTime.now());
      repository.saveAndFlush(entity);
    }, () -> {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
    });

  }

  @Override
  public WarehouseItemResponse getById(String id) {
    WarehouseItem entity =  getEntityById(id).
        orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Id Tidak Ditemukan"));
    WarehouseItemResponse response = new WarehouseItemResponse();
    
    response.setId(id);
    response.setQty(entity.getQty());
    response.setUpdatedAt(entity.getUpdatedAt());
    response.setVersion(entity.getVer());
    SupplierItemResponse detail =  new SupplierItemResponse();
    SupplierItem sup = entity.getSupplierItem();
    detail.setId(sup.getId());
    detail.setName(sup.getName());
    detail.setPrice(sup.getPrice());
    detail.setUserId(sup.getUser().getId());
    response.setDetails(detail);

  return response;

  }

  @Override
  public Optional<WarehouseItem> getEntityById(String id) {
    return repository.findById(id);
  }

  private Optional<WarehouseItem> getEntityBySupplierItemId(String supplierItemId) {
    return repository.findBySupplierItemId(supplierItemId);
  }
  
  @Override
  public List<WarehouseItemResponse> getAll() {
    List<WarehouseItem> warehouseItems = repository.findAll();
    
    List<WarehouseItemResponse> responses = new ArrayList<>();
    
    for (WarehouseItem entity : warehouseItems) {
      WarehouseItemResponse response = new WarehouseItemResponse();
      
      response.setId(entity.getId());
      response.setQty(entity.getQty());
      response.setUpdatedAt(entity.getUpdatedAt());
      response.setVersion(entity.getVer());
      SupplierItemResponse detail =  new SupplierItemResponse();
      SupplierItem sup = entity.getSupplierItem();
      detail.setId(sup.getId());
      detail.setName(sup.getName());
      detail.setPrice(sup.getPrice());
      detail.setUserId(sup.getUser().getId());
      response.setDetails(detail);

      responses.add(response);
  }
     
    return responses;
    
  }

  @Transactional
  @Override
  public void delete(String id) {
    repository.findById(id).ifPresentOrElse(entity -> repository.delete(entity) 
    ,() ->{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Tidak Ditemukan");
    } );

  }



}
