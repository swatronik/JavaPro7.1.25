package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.Product;
import org.example.exception.NotEnoughMoney;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductService() {
    }

    public Product getProduct(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> getProductByUserId(Long id) {
        return repository.findAllByUserId(id);
    }

    public List<Product> getAllProduct(Product product) {
        return repository.findAll();
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Double changeBalanceAccount(Long id, Double deltaBalance) {
        Product product = getProduct(id);
        Double balance = product.getBalance();
        if (deltaBalance < 0 && balance + deltaBalance < 0) {
            throw new NotEnoughMoney(balance, deltaBalance);
        }
        product.setBalance(balance + deltaBalance);
        repository.save(product);
        return getProduct(id).getBalance();
    }
}
