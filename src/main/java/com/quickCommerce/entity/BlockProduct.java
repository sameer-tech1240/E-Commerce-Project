package com.quickCommerce.entity;

import jakarta.persistence.*;

@Entity
public class BlockProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String productId;

  // Getters & Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getProductId() { return productId; }
  public void setProductId(String productId) { this.productId = productId; }


}
