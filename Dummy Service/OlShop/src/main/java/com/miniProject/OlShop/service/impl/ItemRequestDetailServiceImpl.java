package com.miniProject.OlShop.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.miniProject.OlShop.entity.ItemRequestDetail;
import com.miniProject.OlShop.model.request.CreateItemRequestDetailRequest;
import com.miniProject.OlShop.model.request.PagingRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestDetailRequest;
import com.miniProject.OlShop.model.response.ItemRequestDetailResponse;
import com.miniProject.OlShop.service.ItemRequestDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ItemRequestDetailServiceImpl implements ItemRequestDetailService {

  @Transactional
  @Override
  public void add(CreateItemRequestDetailRequest request) {
    // TODO Auto-generated method stub
    
  }

  @Transactional
  @Override
  public void edit(UpdateItemRequestDetailRequest request) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public ItemRequestDetailResponse getById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<ItemRequestDetail> getEntityById(String id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Page<ItemRequestDetailResponse> getAll(PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  @Transactional
  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    
  }

}
