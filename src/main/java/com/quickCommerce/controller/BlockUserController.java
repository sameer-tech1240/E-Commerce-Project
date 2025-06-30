package com.quickCommerce.controller;

import com.quickCommerce.entity.BlockUser;
import com.quickCommerce.service.BlockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blockUsers")
public class BlockUserController {

    @Autowired
    private BlockUserService service;

    @GetMapping("/all")
    public List<BlockUser> getAll() {
        return service.getAll();
    }

    @PostMapping
    public BlockUser save(@RequestBody BlockUser u) {
        return service.save(u);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @DeleteMapping("/deleteByUserId/{userId}")
    public void deleteByUserId(@PathVariable("userId") String userId) {
        service.deleteByUserId(userId);
    }

    // Get by userId
    @GetMapping("/getByUserId/{userId}")
    public Optional<BlockUser> getByUserId(@PathVariable("userId") Long userId) {
        return service.getById(userId);
    }
}
