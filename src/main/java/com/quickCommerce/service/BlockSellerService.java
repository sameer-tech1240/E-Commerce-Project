package com.quickCommerce.service;

import com.quickCommerce.entity.BlockSeller;
import com.quickCommerce.repo.BlockSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockSellerService {

    @Autowired
    private BlockSellerRepository repo;

    public List<BlockSeller> getAll() {
        return repo.findAll();
    }

    public BlockSeller getBySellerId(String sellerId) {
        BlockSeller seller = repo.findBySellerId(sellerId);
        if (seller == null) {
            throw new RuntimeException("Seller not found with sellerId: " + sellerId);
        }
        return seller;
    }

    public BlockSeller save(BlockSeller s) {
        if (s.getId() == 0) {
            return repo.save(s); // new seller
        } else {
            BlockSeller existing = repo.findById(s.getId())
                    .orElseThrow(() -> new RuntimeException("Seller not found to update"));
            existing.setSellerId(s.getSellerId());
            return repo.save(existing);
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    //  New: delete by sellerId
    public void deleteBySellerId(String sellerId) {
        BlockSeller seller = repo.findBySellerId(sellerId);
        if (seller != null) {
            repo.deleteById(seller.getId());
        } else {
            throw new RuntimeException("Seller not found with sellerId: " + sellerId);
        }
    }
}
