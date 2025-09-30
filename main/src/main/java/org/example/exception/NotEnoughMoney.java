package org.example.exception;

public class NotEnoughMoney extends RuntimeException {

    public NotEnoughMoney(Double balance, Double deltaBalance) {
        super(String.format("На счете не достаточно денег! Всего: %s, изменение: %s", balance, deltaBalance));
    }
}
