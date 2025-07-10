package com.quickCommerce.repo;

import com.quickCommerce.entity.BlockUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockUserRepository extends JpaRepository<BlockUser, Long> {
  public  Optional findById(Long id);
 public   BlockUser findByUserId(String userId);
}
