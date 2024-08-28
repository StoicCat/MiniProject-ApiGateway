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
@Table(name = "purchase_transaction_details")
public class PurchaseTransactionDetail extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "purchase_transaction_id", nullable = false)
	private PurchaseTransaction purchaseTransaction;

	@ManyToOne
	@JoinColumn(name = "supplier_item_id", nullable = false)
	private SupplierItem supplierItem;

	@Column(name = "qty", nullable = false)
	private Integer qty;

	@Column(name = "total_price", nullable = false)
	private BigDecimal totalPrice;
}
