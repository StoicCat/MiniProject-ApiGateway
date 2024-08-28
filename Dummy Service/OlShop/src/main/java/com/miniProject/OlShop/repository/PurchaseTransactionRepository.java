package com.miniProject.OlShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject.OlShop.entity.PurchaseTransaction;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, String> {

}
