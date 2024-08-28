package com.miniProject.OlShop.service;

import java.util.List;
import java.util.Optional;
import com.miniProject.OlShop.entity.WarehouseItem;
import com.miniProject.OlShop.model.request.CreateWarehouseItemRequest;
import com.miniProject.OlShop.model.request.UpdateWarehouseItemRequest;
import com.miniProject.OlShop.model.response.WarehouseItemResponse;

public interface WarehouseItemService {

  void add (CreateWarehouseItemRequest request);
  
  void edit (UpdateWarehouseItemRequest request);
  
  WarehouseItemResponse getById (String id);
  
  Optional<WarehouseItem> getEntityById (String id);
  
  List<WarehouseItemResponse> getAll();
  
  void delete(String id);
  
}
