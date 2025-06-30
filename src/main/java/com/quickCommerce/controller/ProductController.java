package com.quickCommerce.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.quickCommerce.entity.Product;
import com.quickCommerce.service.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    // POST method to add a new product
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(@RequestParam("sellerId") Long sellerId,
                              @RequestParam("productName") String productName,
                              @RequestParam("category") String category,
                              @RequestParam("price") Double price,
                              @RequestParam("discount") Double discount,
                              @RequestParam(value = "discountedPrice", required = false)  Double discountedPrice,
                              @RequestParam("quantity") Integer quantity,
                              @RequestParam("frontImage") MultipartFile frontImage,
                              @RequestParam(value = "img1", required = false) MultipartFile img1,
                              @RequestParam(value = "img2", required = false) MultipartFile img2,
                              @RequestParam(value = "img3", required = false) MultipartFile img3,
                              @RequestParam(value = "img4", required = false) MultipartFile img4,
                              @RequestParam(value = "img5", required = false) MultipartFile img5
    ) throws IOException {

        if (price == null || discount == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price and discount must not be null");
        }

        Product product = new Product();
        product.setSellerId(sellerId);
        product.setProductName(productName);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setQuantity(quantity);
        product.setFrontImage(frontImage.getBytes());

        if (img1 != null && !img1.isEmpty()) product.setImg1(img1.getBytes());
        if (img2 != null && !img2.isEmpty()) product.setImg2(img2.getBytes());
        if (img3 != null && !img3.isEmpty()) product.setImg3(img3.getBytes());
        if (img4 != null && !img4.isEmpty()) product.setImg4(img4.getBytes());
        if (img5 != null && !img5.isEmpty()) product.setImg5(img5.getBytes());

        double discountAmount = price * discount / 100;
        product.setDiscountedPrice(price - discountAmount);

        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @GetMapping("/search")
    public List<Product> getProductsByName(@RequestParam String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Product> getProductsBySellerId(@PathVariable Long sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }
}
