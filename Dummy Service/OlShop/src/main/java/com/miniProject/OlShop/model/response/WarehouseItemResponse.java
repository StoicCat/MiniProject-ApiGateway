package com.miniProject.OlShop.model.response;

import java.time.LocalDateTime;
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
  private Integer version;
  private LocalDateTime updatedAt;
  private String updatedBy;

  private Integer qty;

  SupplierItemResponse details;


}
