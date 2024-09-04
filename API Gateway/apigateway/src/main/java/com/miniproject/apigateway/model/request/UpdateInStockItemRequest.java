package com.miniproject.apigateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateInStockItemRequest {
	private String id;
	private Integer version;
	private Integer qty;
	private String supplierItemId;
	private Integer warehouseItemVersion;
	private Integer warehouseItemQty;
}
