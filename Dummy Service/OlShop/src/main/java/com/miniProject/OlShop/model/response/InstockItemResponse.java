package com.miniProject.OlShop.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstockItemResponse {
	private String id;
	private String supplierItemId;
	private String supplierItemName;
	private String supplierItemPrice;
	private Integer quantity;
}
