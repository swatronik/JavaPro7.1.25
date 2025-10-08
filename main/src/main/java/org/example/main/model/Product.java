package org.example.main.model;

import org.example.main.model.enums.TypeProduct;

public class Product {

    private Long id;

    private Long numberAccount;

    private Double balance;

    private TypeProduct typeProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Long numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", numberAccount=" + numberAccount +
                ", balance=" + balance +
                ", typeProduct=" + typeProduct +
                '}';
    }
}
