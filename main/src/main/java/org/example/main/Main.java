package org.example.main;


import org.example.main.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductService productService) {
        return (args) -> {
            productService.getProductByClient(1L);
            //productService.changeBalance(1L, TypeProduct.CARD, 150.0); // Нет такого типа продукта на клиенте
            //productService.changeBalance(2L, TypeProduct.CARD, -3500.0); // На счете недостаточно денег
        };
    }
}