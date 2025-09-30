package org.example.api;

import org.example.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestProductSender extends BaseRestSender {

    @Value("${service.product.url}")
    private String urlService;

    private final static String PATH_GET_ALL_PRODUCT_CLIENT = "/product/user/{id}";
    private final static String PATH_POST_CHANGE_BALANCE_PRODUCT = "/product/{id}/change-balance";

    public List<Product> getProductByClient(Long id) {
        ResponseEntity<Product[]> response = restTemplate.getForEntity(urlService + PATH_GET_ALL_PRODUCT_CLIENT, Product[].class, id);
        return responseToArray(response);
    }

    public Double postChangeBalanceProduct(Long id, Double deltaBalance) {
        ResponseEntity<Double> response = restTemplate.postForEntity(urlService + PATH_POST_CHANGE_BALANCE_PRODUCT, deltaBalance, Double.class, id);
        return response.getBody();
    }

}
