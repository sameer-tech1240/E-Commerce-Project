package com.quickCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quickCommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
