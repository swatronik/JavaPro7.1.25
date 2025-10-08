package org.example.main.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseRestSender {

    public RestTemplate restTemplate = new RestTemplate();

    public  <T> List<T> responseToArray(ResponseEntity<T[]> response) {
        return response.getBody() != null ? Arrays.stream(response.getBody()).toList() : new ArrayList<>();
    }
}
