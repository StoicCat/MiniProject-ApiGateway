package com.miniProject.OlShop.service;

import java.util.List;
import java.util.Optional;
import com.miniProject.OlShop.entity.ItemRequestDetail;
import com.miniProject.OlShop.model.request.CreateItemRequestDetailRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestDetailRequest;
import com.miniProject.OlShop.model.response.ItemRequestDetailResponse;

public interface ItemRequestDetailService {

  
  void add(CreateItemRequestDetailRequest request);

  void edit(UpdateItemRequestDetailRequest request);

  ItemRequestDetailResponse getById(String id);

  Optional<ItemRequestDetail> getEntityById(String id);

  List<ItemRequestDetailResponse> getAll();

  void delete(String id);
  
  
}
