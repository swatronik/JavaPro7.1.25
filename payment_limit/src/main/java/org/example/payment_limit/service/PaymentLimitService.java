package org.example.payment_limit.service;

import jakarta.transaction.Transactional;
import org.example.payment_limit.entity.PaymentLimitEntity;
import org.example.payment_limit.exception.NotEnoughLimit;
import org.example.payment_limit.repository.ConfigRepository;
import org.example.payment_limit.repository.PaymentLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class PaymentLimitService {

    @Autowired
    private PaymentLimitRepository paymentLimitRepository;

    @Autowired
    private ConfigRepository configRepository;

    public PaymentLimitService() {
    }

    public Double getActualLimit() {
        return configRepository.getActualLimit(LocalDate.now());
    }

    public PaymentLimitEntity findByUserid(Long userId) {
        return paymentLimitRepository.findByUserId(userId);
    }

    @Transactional
    public Double changeLimitBalanceAccount(Long id, Double deltaBalance) {
        Double actualLimit = getActualLimit();
        PaymentLimitEntity paymentLimitEntity = findByUserid(id);
        Double actualLimitUser = paymentLimitEntity != null && paymentLimitEntity.getLastUpdateDate().equals(LocalDate.now()) ? actualLimit - paymentLimitEntity.getSpendingLimit() : actualLimit;

        if (deltaBalance > actualLimitUser) {
            throw new NotEnoughLimit(id, deltaBalance, actualLimitUser);
        }

        if (paymentLimitEntity == null) {
            paymentLimitEntity = new PaymentLimitEntity();
            paymentLimitEntity.setUserId(id);
            paymentLimitEntity.setLastUpdateDate(LocalDate.now());
            paymentLimitEntity.setSpendingLimit(deltaBalance);
        } else {
            paymentLimitEntity.setLastUpdateDate(LocalDate.now());
            paymentLimitEntity.setSpendingLimit(paymentLimitEntity.getSpendingLimit() + deltaBalance);
        }
        paymentLimitRepository.save(paymentLimitEntity);
        return actualLimit - paymentLimitEntity.getSpendingLimit();
    }
}
