package com.miniProject.OlShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniProject.OlShop.entity.ItemRequestDetail;

public interface ItemRequestDetailRepository extends JpaRepository<ItemRequestDetail, String> {
	List<ItemRequestDetail> findAllByItemRequestId(String itemRequestId);
}
