package com.miniProject.OlShop.service;

import java.util.List;
import java.util.Optional;
import com.miniProject.OlShop.entity.SupplierItem;
import com.miniProject.OlShop.model.request.CreateSupplierItemRequest;
import com.miniProject.OlShop.model.request.UpdateSupplierItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;

public interface SupplierItemService {
  void add(CreateSupplierItemRequest request);

  void edit(UpdateSupplierItemRequest request);

  SupplierItemResponse getById(String id);

  
  Optional<SupplierItem> getEntityById(String id);

//  Page<SupplierItemResponse> getAll(PagingRequest pagingRequest);
List<SupplierItemResponse> getAll();

  void delete(String id);
}
