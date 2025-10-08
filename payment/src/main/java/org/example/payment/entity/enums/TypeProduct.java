package org.example.payment.entity.enums;

public enum TypeProduct {
    ACCOUNT("Счет"),
    CARD("Карта");

    private final String description;

    TypeProduct(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
