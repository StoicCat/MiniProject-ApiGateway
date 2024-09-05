package com.miniProject.OlShop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseTransactionDetailRequest {

	private String supplierItemId;
	private Integer quantity;
	private String createBy;
}
