package com.miniproject.apigateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInStockItemRequest {
	private String supplierItemId;
	private Integer quantity;
}
