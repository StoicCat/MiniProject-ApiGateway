package com.miniProject.OlShop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "warehouse_items")
public class WarehouseItem extends BaseModel {
	@OneToOne
	@JoinColumn(name = "supplier_item_id", nullable = false)
	private SupplierItem supplierItem;

	@Column(name = "qty", nullable = false)
	private Integer qty;
}
