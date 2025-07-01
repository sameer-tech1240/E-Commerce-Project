package com.quickCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickCommerce.entity.BlockSeller;


public interface BlockSellerRepository extends JpaRepository<BlockSeller, Long>{
	BlockSeller findBySellerId(String sellerId);
}
