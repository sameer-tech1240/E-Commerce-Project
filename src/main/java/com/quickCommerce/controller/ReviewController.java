package com.quickCommerce.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickCommerce.entity.Review;
import com.quickCommerce.service.ReviewService;



@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;	
	@GetMapping
	public List<Review>getAllReviews(){
	return reviewService. getAllReviews();
	}
	@PostMapping("/add")
	public Review addReview(@RequestBody Review review) {
		
	    return reviewService.addReview(review);
	}
	  @GetMapping("/product/{productId}")
	    public List<Review> getReviewByProductId(@PathVariable long productId) {
	        return reviewService.getReviewsByProductId(productId);
	    }
	 
	    @GetMapping("/user/{userId}")
	    public List<Review> getReviewByUserId(@PathVariable long userId) {
	        return reviewService.getReviewsByUserId(userId);
	    }
	

	    

}
