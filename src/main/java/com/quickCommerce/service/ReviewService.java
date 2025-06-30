package com.quickCommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickCommerce.entity.Review;
import com.quickCommerce.repo.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewrepository;
	
	public Review addReview(Review review) {
		return  reviewrepository.save(review);
		
	}
		
	public List<Review> getAllReviews(){
		return reviewrepository.findAll();
		
		
	}
	  public List<Review> getReviewsByProductId(long productId) {
	        return reviewrepository.findByProductId(productId);
	    }

	    public List<Review> getReviewsByUserId(long userId) {
	        return reviewrepository.findByUserId(userId);
	    }
	

}
