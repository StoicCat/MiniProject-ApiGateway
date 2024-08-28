package com.miniProject.OlShop.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.miniProject.OlShop.entity.SupplierItem;
import com.miniProject.OlShop.model.request.CreateSupplierItemRequest;
import com.miniProject.OlShop.model.request.PagingRequest;
import com.miniProject.OlShop.model.request.UpdateSupplierItemRequest;
import com.miniProject.OlShop.model.response.SupplierItemResponse;

public interface SupplierItemService {
  void add(CreateSupplierItemRequest request);

  void edit(UpdateSupplierItemRequest request);

  SupplierItemResponse getById(String id);

  Optional<SupplierItem> getEntityById(String id);

  Page<SupplierItemResponse> getAll(PagingRequest pagingRequest);

  void delete(String id);
}
