package org.example.controller;

import org.example.exception.NotEnoughMoney;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProduct(id));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProductByUserId(id));
    }

    @PostMapping(path = "/{id}/change-balance")
    public ResponseEntity<?> changeBalanceAccount(@PathVariable("id") Long id, @RequestBody Double deltaBalance) {
        return ResponseEntity.ok(service.changeBalanceAccount(id, deltaBalance));
    }
}
