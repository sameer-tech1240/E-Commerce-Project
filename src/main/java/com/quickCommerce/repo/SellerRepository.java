package com.quickCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickCommerce.entity.Seller;


public interface SellerRepository extends JpaRepository<Seller, Long>{
	Seller findBySellerId(String sellerId);
}
