package com.miniProject.OlShop.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.miniProject.OlShop.entity.ItemRequestDetail;
import com.miniProject.OlShop.model.request.CreateItemRequestDetailRequest;
import com.miniProject.OlShop.model.request.PagingRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestDetailRequest;
import com.miniProject.OlShop.model.response.ItemRequestDetailResponse;

public interface ItemRequestDetailService {

  
  void add(CreateItemRequestDetailRequest request);

  void edit(UpdateItemRequestDetailRequest request);

  ItemRequestDetailResponse getById(String id);

  Optional<ItemRequestDetail> getEntityById(String id);

  Page<ItemRequestDetailResponse> getAll(PagingRequest pagingRequest);

  void delete(String id);
  
  
}
