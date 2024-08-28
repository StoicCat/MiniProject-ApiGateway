package com.miniProject.OlShop.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.miniProject.OlShop.entity.ItemRequest;
import com.miniProject.OlShop.model.request.CreateItemRequestRequest;
import com.miniProject.OlShop.model.request.PagingRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestRequest;
import com.miniProject.OlShop.model.response.ItemRequestResponse;

public interface ItemRequestService {
  
  void add(CreateItemRequestRequest request);

  void edit(UpdateItemRequestRequest request);

  ItemRequestResponse getById(String id);

  Optional<ItemRequest> getEntityById(String id);

  Page<ItemRequestResponse> getAll(PagingRequest pagingRequest);

  void delete(String id);
}
