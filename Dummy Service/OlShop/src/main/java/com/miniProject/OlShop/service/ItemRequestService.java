package com.miniProject.OlShop.service;

import java.util.List;
import java.util.Optional;
import com.miniProject.OlShop.entity.ItemRequest;
import com.miniProject.OlShop.model.request.CreateItemRequestRequest;
import com.miniProject.OlShop.model.request.UpdateItemRequestRequest;
import com.miniProject.OlShop.model.response.ItemRequestResponse;

public interface ItemRequestService {
  
  String add(CreateItemRequestRequest request);

  void acc(UpdateItemRequestRequest request);
  
  void dcl(UpdateItemRequestRequest request);

  ItemRequestResponse getById(String id);

  Optional<ItemRequest> getEntityById(String id);

  List<ItemRequestResponse> getAll();

  void delete(String id);
}
