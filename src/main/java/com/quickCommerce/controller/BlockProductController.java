package com.quickCommerce.controller;

import com.quickCommerce.entity.BlockProduct;
import com.quickCommerce.service.BlockProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blockProducts")
public class BlockProductController {

  @Autowired
  private BlockProductService service;

  @GetMapping("/all")
  public List<BlockProduct> getAll() {
    return service.getAll();
  }
  @GetMapping("/{productId}")
  public Optional<BlockProduct> getProductById(@PathVariable String productId) {
      return service.getProductById(productId);
  }

  @PostMapping
  public BlockProduct save(@RequestBody BlockProduct p) {
    return service.save(p);
  }

  
  @DeleteMapping("/unblockByProductId/{productId}")
  public BlockProduct unblock(@PathVariable String productId) {
    return service.unblock(productId);
  }

  @DeleteMapping("/unblockById/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
