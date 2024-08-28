package com.miniProject.OlShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject.OlShop.entity.InStockItem;

public interface InStockItemRepository extends JpaRepository<InStockItem, String> {

}
