package com.miniProject.OlShop.model.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingRequest {

  private Integer page;

  private Integer pageSize;

  private List<SortBy> sortBy;

}

