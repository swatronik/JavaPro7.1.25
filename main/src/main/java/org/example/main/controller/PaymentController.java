package org.example.main.controller;

import org.example.main.model.enums.TypeProduct;
import org.example.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private ProductService service;

    @GetMapping(path = "/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProductByClient(id));
    }

    @PostMapping(path = "/product/{id}/change-balance")
    public ResponseEntity<?> changeBalance(@PathVariable("id") Long id, @RequestParam("product") TypeProduct typeProduct, @RequestBody Double deltaBalance) {
        return ResponseEntity.ok(service.changeBalance(id, typeProduct, deltaBalance));
    }
}