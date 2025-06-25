package com.quickCommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quickCommerce.entity.Product;
import com.quickCommerce.repo.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ✅ POST: Add Product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        double discountAmount = product.getPrice() * product.getDiscount() / 100;
        product.setDiscountedPrice(product.getPrice() - discountAmount);
        return productRepository.save(product);
    }

    // ✅ GET: Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ✅ GET: Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
