package com.quickCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quickCommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
