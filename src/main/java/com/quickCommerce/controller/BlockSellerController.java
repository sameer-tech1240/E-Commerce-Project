package com.quickCommerce.controller;

import com.quickCommerce.entity.BlockSeller;
import com.quickCommerce.service.BlockSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocksellers")
public class BlockSellerController {

    @Autowired
    private BlockSellerService service;

    @GetMapping("/all")
    public List<BlockSeller> getAll() {
        return service.getAll();
    }

    @GetMapping("/getBySellerId/{sellerId}")
    public BlockSeller getBySellerId(@PathVariable("sellerId") String sellerId) {
        return service.getBySellerId(sellerId);
    }

    @PostMapping
    public BlockSeller save(@RequestBody BlockSeller s) {
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
