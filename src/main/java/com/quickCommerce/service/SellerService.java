package com.quickCommerce.service;

import com.quickCommerce.entity.Seller;
import com.quickCommerce.repo.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository repo;

    public List<Seller> getAll() {
        return repo.findAll();
    }

    public Seller getBySellerId(String sellerId) {
        Seller seller = repo.findBySellerId(sellerId);
        if (seller == null) {
            throw new RuntimeException("Seller not found with sellerId: " + sellerId);
        }
        return seller;
    }

    public Seller save(Seller s) {
        if (s.getId() == 0) {
            return repo.save(s); // new seller
        } else {
            Seller existing = repo.findById(s.getId())
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
        Seller seller = repo.findBySellerId(sellerId);
        if (seller != null) {
            repo.deleteById(seller.getId());
        } else {
            throw new RuntimeException("Seller not found with sellerId: " + sellerId);
        }
    }
}
