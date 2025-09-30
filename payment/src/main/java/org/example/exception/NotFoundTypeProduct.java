package org.example.exception;

import org.example.model.enums.TypeProduct;

public class NotFoundTypeProduct extends RuntimeException {

    public NotFoundTypeProduct(Long id, TypeProduct typeProduct) {
        super(String.format("У пользователя с id: %s не найден продукт: %s", id, typeProduct));
    }
}
