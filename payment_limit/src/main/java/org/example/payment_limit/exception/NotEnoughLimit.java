package org.example.payment_limit.exception;

public class NotEnoughLimit extends RuntimeException {
    public NotEnoughLimit(Long userId, Double deltaBalance, Double actualLimit) {
        super(String.format("Лимита %s пользователя (id: %s) недостаточно для снятия %s", actualLimit, userId, deltaBalance));
    }
}
