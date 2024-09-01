package com.miniproject.apigateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateInStockItemRequest extends CreateInStockItemRequest {
	private String id;
	private Integer version;
}
