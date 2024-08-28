package com.miniProject.OlShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject.OlShop.entity.PurchaseTransactionDetail;

public interface PurchaseTransactionDetailRepository extends JpaRepository<PurchaseTransactionDetail, String> {
	List<PurchaseTransactionDetail> findAllByPurchaseTransactionId(String purchaseTransactionId);
}
