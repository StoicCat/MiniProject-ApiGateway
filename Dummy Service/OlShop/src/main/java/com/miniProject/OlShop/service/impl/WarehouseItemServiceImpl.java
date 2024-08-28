package com.miniProject.OlShop.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.miniProject.OlShop.entity.WarehouseItem;
import com.miniProject.OlShop.model.request.CreateWarehouseItemRequest;
import com.miniProject.OlShop.model.request.PagingRequest;
import com.miniProject.OlShop.model.request.UpdateWarehouseItemRequest;
import com.miniProject.OlShop.model.response.WarehouseItemResponse;
import com.miniProject.OlShop.service.WarehouseItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WarehouseItemServiceImpl implements WarehouseItemService {

  @Transactional
  @Override
  public void add(CreateWarehouseItemRequest request) {
    // TODO Auto-generated method stub
    
  }

  @Transactional
  @Override
  public void edit(UpdateWarehouseItemRequest request) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public WarehouseItemResponse getById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<WarehouseItem> getEntityById(String id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Page<WarehouseItemResponse> getAll(PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  @Transactional
  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    
  }

  
  
}
