package org.example.payment.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestLimitSender extends BaseRestSender {

    @Value("${service.limit.url}")
    private String urlService;

    private final static String PATH_POST_CHANGE_LIMIT_BALANCE_ACCOUNT = "/payment-limit/change-limit/{id}";

    public Double postChangeLimitBalanceAccount(Long id, Double deltaLimit) {
        ResponseEntity<Double> response = restTemplate.postForEntity(urlService + PATH_POST_CHANGE_LIMIT_BALANCE_ACCOUNT, deltaLimit, Double.class, id);
        return response.getBody();
    }
}

