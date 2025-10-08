package org.example.payment_limit.repository;

import org.example.payment_limit.entity.PaymentLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentLimitRepository extends JpaRepository<PaymentLimitEntity, Long>, JpaSpecificationExecutor<PaymentLimitEntity> {

    PaymentLimitEntity findByUserId(Long userId);
}
