package org.example.service;

import org.example.entity.Product;
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
}
