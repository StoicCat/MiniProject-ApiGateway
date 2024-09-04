package com.miniproject.apigateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInStockItemRequest {
	private Integer qty;
	private String supplierItemId;
	private Integer warehouseItemQty;
	private Integer warehouseItemVersion;
	
}
