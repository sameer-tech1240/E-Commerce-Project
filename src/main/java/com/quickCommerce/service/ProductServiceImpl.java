package com.quickCommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickCommerce.entity.Product;
import com.quickCommerce.repo.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(this::filterNullImages);
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    private void filterNullImages(Product product) {
        if (product.getImg1() == null || product.getImg1().length == 0) product.setImg1(null);
        if (product.getImg2() == null || product.getImg2().length == 0) product.setImg2(null);
        if (product.getImg3() == null || product.getImg3().length == 0) product.setImg3(null);
        if (product.getImg4() == null || product.getImg4().length == 0) product.setImg4(null);
        if (product.getImg5() == null || product.getImg5().length == 0) product.setImg5(null);
    }
}
