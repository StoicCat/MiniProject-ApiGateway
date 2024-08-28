package com.miniProject.OlShop.model.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTransactionDetailResponse {
	private String id;
	private String purchaseTransactionId;
	private String supplierItemId;
	private Integer quantity;
	private BigDecimal totalPrice;
}
