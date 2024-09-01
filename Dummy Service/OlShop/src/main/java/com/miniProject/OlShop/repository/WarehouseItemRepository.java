package com.miniProject.OlShop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject.OlShop.entity.WarehouseItem;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, String> {
	Optional<WarehouseItem>findBySupplierItemId(String supplierItemId);
}
