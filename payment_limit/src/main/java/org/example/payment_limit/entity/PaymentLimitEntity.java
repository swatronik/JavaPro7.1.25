package org.example.payment_limit.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment_limit")
public class PaymentLimitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Double spendingLimit;

    private LocalDate lastUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getSpendingLimit() {
        return spendingLimit;
    }

    public void setSpendingLimit(Double spendingLimit) {
        this.spendingLimit = spendingLimit;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "PaymentLimitEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", spendingLimit='" + spendingLimit + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}