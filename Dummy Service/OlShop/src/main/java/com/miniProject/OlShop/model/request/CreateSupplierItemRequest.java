package com.miniProject.OlShop.model.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierItemRequest {

  private String userId;
	
  private String name;
  
  private BigDecimal price;
  
}
