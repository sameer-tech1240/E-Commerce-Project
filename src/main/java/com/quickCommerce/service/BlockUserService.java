package com.quickCommerce.service;

import com.quickCommerce.entity.BlockUser;
import com.quickCommerce.repo.BlockUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; 

@Service
public class BlockUserService {

    @Autowired
    private BlockUserRepository repo;

    public List<BlockUser> getAll() {
        return repo.findAll();
    }

    public BlockUser save(BlockUser u) {
        return repo.save(u);
    }

    //  Delete by ID
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    //  Delete by userId
    public void deleteByUserId(String userId) {
        BlockUser u = repo.findByUserId(userId);
        if (u != null) {
            repo.deleteById(u.getId());
        } else {
            throw new RuntimeException("User not found with userId: " + userId);
        }
        
    }
    public Optional<BlockUser> getById(Long id){
    	
		return repo.findById(id);
    	
    }
}
