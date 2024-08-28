package com.miniProject.OlShop.model.request;

import com.miniProject.OlShop.model.enums.SortByDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortBy {

  private String propertyName;

  private SortByDirection direction;

}
