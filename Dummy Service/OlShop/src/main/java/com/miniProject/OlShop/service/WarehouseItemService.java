package com.miniProject.OlShop.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.miniProject.OlShop.entity.WarehouseItem;
import com.miniProject.OlShop.model.request.CreateWarehouseItemRequest;
import com.miniProject.OlShop.model.request.PagingRequest;
import com.miniProject.OlShop.model.request.UpdateWarehouseItemRequest;
import com.miniProject.OlShop.model.response.WarehouseItemResponse;

public interface WarehouseItemService {

  void add (CreateWarehouseItemRequest request);
  
  void edit (UpdateWarehouseItemRequest request);
  
  WarehouseItemResponse getById (String id);
  
  Optional<WarehouseItem> getEntityById (String id);
  
  Page<WarehouseItemResponse> getAll(PagingRequest pagingRequest);
  
  void delete(String id);
  
}
