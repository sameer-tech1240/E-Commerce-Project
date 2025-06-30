package com.quickCommerce.service;

import java.util.List;
import com.quickCommerce.entity.Product;

public interface IProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByName(String name);
    List<Product> getProductsBySellerId(Long sellerId);
    List<Product> getProductsByCategory(String category);
}
