package com.miniProject.OlShop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierItemRequest extends CreateSupplierItemRequest {
  private String id;
  
  private String version;
  
}
