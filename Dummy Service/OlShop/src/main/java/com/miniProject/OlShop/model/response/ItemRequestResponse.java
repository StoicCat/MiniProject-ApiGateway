package com.miniProject.OlShop.model.response;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestResponse {

  private String id;
  private Long version;
  private ZonedDateTime updatedAt;
  private String updatedBy;
  
  private String userSupplierId;
  private String acceptanceStatus;
  
  
  
}
