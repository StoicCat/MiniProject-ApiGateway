package com.miniProject.OlShop.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "supplier_items")
public class SupplierItem extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "price", nullable = false)
	private BigDecimal price;
}
