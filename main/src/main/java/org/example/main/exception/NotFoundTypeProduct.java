package org.example.main.exception;

import org.example.main.model.enums.TypeProduct;

public class NotFoundTypeProduct extends RuntimeException {

    public NotFoundTypeProduct(Long id, TypeProduct typeProduct) {
        super(String.format("У пользователя с id: %s не найден продукт: %s", id, typeProduct));
    }
}
