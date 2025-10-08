package org.example.payment_limit.controller;

import org.example.payment_limit.service.PaymentLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment-limit")
public class PaymentLimitController {

    @Autowired
    private PaymentLimitService service;

    @PostMapping(path = "/change-limit/{id}")
    public ResponseEntity<?> changeLimitBalanceAccount(@PathVariable("id") Long id, @RequestBody Double deltaLimit) {
        return ResponseEntity.ok(service.changeLimitBalanceAccount(id, deltaLimit));
    }
}