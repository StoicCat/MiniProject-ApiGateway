package com.miniproject.apigateway.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InStockItemResponse {
	private String id;
	private String supplierItemId;
	private String supplierItemName;
	private BigDecimal supplierItemPrice;
	private Integer quantity;
}
