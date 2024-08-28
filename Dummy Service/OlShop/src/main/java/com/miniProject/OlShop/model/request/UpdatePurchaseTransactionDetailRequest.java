package com.miniProject.OlShop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePurchaseTransactionDetailRequest extends CreatePurchaseTransactionDetailRequest {
	private String id;
	private Integer version;
}
