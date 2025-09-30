package org.example.service;


import org.example.api.RestProductSender;
import org.example.exception.NotFoundTypeProduct;
import org.example.model.Product;
import org.example.model.enums.TypeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    RestProductSender restProductSender;

    public List<Product> getProductByClient(Long id) {
        return restProductSender.getProductByClient(id);
    }

    public Double changeBalance(Long id, TypeProduct typeProduct, Double deltaBalance) {
        Long idProduct = getProductByClient(id).stream().filter(product -> product.getTypeProduct().equals(typeProduct)).findFirst().orElseThrow(() -> new NotFoundTypeProduct(id, typeProduct)).getId();
        return restProductSender.postChangeBalanceProduct(idProduct, deltaBalance);
    }
}
