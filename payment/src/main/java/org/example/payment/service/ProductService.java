package org.example.payment.service;

import jakarta.transaction.Transactional;
import org.example.payment.api.RestLimitSender;
import org.example.payment.entity.ProductEntity;
import org.example.payment.exception.NotEnoughMoney;
import org.example.payment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    RestLimitSender restLimitSender;

    @Autowired
    private ProductRepository repository;

    public ProductService() {
    }

    public ProductEntity getProduct(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ProductEntity> getProductByUserId(Long id) {
        return repository.findAllByUserId(id);
    }

    public List<ProductEntity> getAllProduct() {
        return repository.findAll();
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Double changeBalanceAccount(Long id, Double deltaBalance) {
        ProductEntity productEntity = getProduct(id);
        Double balance = productEntity.getBalance();
        if (deltaBalance < 0 && balance + deltaBalance < 0) {
            throw new NotEnoughMoney(balance, deltaBalance);
        }
        if (deltaBalance < 0) {
            restLimitSender.postChangeLimitBalanceAccount(productEntity.getUser().getId(), -deltaBalance);
        }
        productEntity.setBalance(balance + deltaBalance);
        repository.save(productEntity);
        return getProduct(id).getBalance();
    }
}
