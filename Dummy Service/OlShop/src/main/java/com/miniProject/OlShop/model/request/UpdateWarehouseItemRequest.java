package com.miniProject.OlShop.model.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWarehouseItemRequest  {

  private String version;
  
  private Integer qty;
  
  private String supplierItemId;
  
}
