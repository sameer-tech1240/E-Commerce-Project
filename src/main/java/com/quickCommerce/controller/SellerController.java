package com.quickCommerce.controller;

import com.quickCommerce.entity.Seller;
import com.quickCommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocksellers")
public class SellerController {

    @Autowired
    private SellerService service;

    @GetMapping("/all")
    public List<Seller> getAll() {
        return service.getAll();
    }

    @GetMapping("/getBySellerId/{sellerId}")
    public Seller getBySellerId(@PathVariable("sellerId") String sellerId) {
        return service.getBySellerId(sellerId);
    }

    @PostMapping
    public Seller save(@RequestBody Seller s) {
        return service.save(s);
    }

    //  Delete by database ID
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.delete(id);
    }

    //  Delete by sellerId
    @DeleteMapping("/deleteBySellerId/{sellerId}")
    public void deleteBySellerId(@PathVariable("sellerId") String sellerId) {
        service.deleteBySellerId(sellerId);
    }
}
