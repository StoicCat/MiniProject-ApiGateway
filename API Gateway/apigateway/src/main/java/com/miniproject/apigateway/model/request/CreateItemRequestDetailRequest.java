package com.miniproject.apigateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemRequestDetailRequest {

	private String itemRequestId;
	  
	private String supplierItemId;
	  
	private Integer qty;
}
