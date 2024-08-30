package com.miniproject.apigateway.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplierItemResponse {
	  private String id;
	  private Integer version;
	  private LocalDateTime updatedAt;
	  private String updatedBy;
	  
	  private String userId;
	  
	  private String name;
	  
	  private BigDecimal price;
}
