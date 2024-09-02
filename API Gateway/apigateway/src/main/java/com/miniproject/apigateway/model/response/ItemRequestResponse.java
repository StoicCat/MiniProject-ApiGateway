package com.miniproject.apigateway.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestResponse {

	private Integer qty;

	private String itemRequestId;
	  
	private String supplierItemId;
}
