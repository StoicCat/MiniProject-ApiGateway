package com.miniProject.OlShop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInStockItemRequest extends CreateInStockItemRequest {
	private String id;
	private Integer version;
}
