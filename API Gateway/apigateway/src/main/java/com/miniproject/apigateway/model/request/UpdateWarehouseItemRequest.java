package com.miniproject.apigateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateWarehouseItemRequest {

	  private Integer version;
	  
	  private Integer qty;
	  
	  private String supplierItemId;
}
