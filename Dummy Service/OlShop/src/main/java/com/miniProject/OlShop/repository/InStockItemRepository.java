package com.miniProject.OlShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniProject.OlShop.entity.InStockItem;

public interface InStockItemRepository extends JpaRepository<InStockItem, String> {
	@Query(value = "SELECT isi FROM InStockItem isi "
			+ "WHERE isi.supplierItem.name LIKE "
			+ "%:inquiry%")
	List<InStockItem> findAllByInquiry(String inquiry);
}
