package com.miniProject.OlShop.model.response;

import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseItemResponse {

  private String id;
  private Long version;
  private ZonedDateTime updatedAt;
  private String updatedBy;
  
  private Integer qty;
  
  List<SupplierItemResponse> details;
  
  
}
