package com.quickCommerce.service;

import com.quickCommerce.entity.BlockProduct;
import com.quickCommerce.repo.BlockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockProductService {

  @Autowired
  private BlockProductRepository repo;

  public List<BlockProduct> getAll() {
    return repo.findAll();
  }
  public Optional<BlockProduct> getProductById(String productId) {
      return repo.findByProductId(productId);
  }

  public BlockProduct save(BlockProduct p) {
    return repo.save(p);
  }

  public BlockProduct block(String productId) {
    // Pehle check karo already blocked hai ya nahi
    if (repo.findByProductId(productId).isPresent()) {
      throw new RuntimeException("Product already blocked");
    }

    BlockProduct blockProduct = new BlockProduct();
    blockProduct.setProductId(productId);
    return repo.save(blockProduct);
  }

  public BlockProduct unblock(String productId) {
    BlockProduct p = repo.findByProductId(productId)
                         .orElseThrow(() -> new RuntimeException("Product not found to unblock"));

    repo.deleteById(p.getId()); // Record delete ho jayega, unblock ho gaya
    return p;
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
}
