package com.quickCommerce.repo;

import com.quickCommerce.entity.BlockProduct;
import com.quickCommerce.entity.BlockUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockProductRepository extends JpaRepository<BlockProduct, Long> {
	
	Optional<BlockProduct> findByProductId(String productId);
}
