package com.miniProject.OlShop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWarehouseItemRequest {

  private Integer qty;
  private String supplierItemId;
}
