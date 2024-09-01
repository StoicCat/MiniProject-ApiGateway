package com.miniproject.apigateway.model.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSupplierItemRequest {

	  private String userId;
	  
	  private String name;
	  
	  private BigDecimal price;
}
