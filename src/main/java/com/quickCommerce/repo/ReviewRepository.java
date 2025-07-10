package com.quickCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.quickCommerce.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByProductId(long productId);   
    List<Review> findByUserId(long userId);
}
       
