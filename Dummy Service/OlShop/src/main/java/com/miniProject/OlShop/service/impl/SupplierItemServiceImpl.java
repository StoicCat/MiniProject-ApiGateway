package com.miniProject.OlShop.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.miniProject.OlShop.entity.SupplierItem;
import com.miniProject.OlShop.model.request.CreateSupplierItemRequest;
import com.miniProject.OlShop.model.request.PagingRequest;
import com.miniProject.OlShop.model.request.UpdateSupplierItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;
import com.miniProject.OlShop.service.SupplierItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierItemServiceImpl implements SupplierItemService{

  @Transactional
  @Override
  public void add(CreateSupplierItemRequest request) {
    // TODO Auto-generated method stub
    
  }

  
  @Transactional
  @Override
  public void edit(UpdateSupplierItemRequest request) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public SupplierItemResponse getById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<SupplierItem> getEntityById(String id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Page<SupplierItemResponse> getAll(PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  @Transactional
  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    
  }

}
