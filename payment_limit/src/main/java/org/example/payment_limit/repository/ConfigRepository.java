package org.example.payment_limit.repository;

import org.example.payment_limit.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ConfigRepository extends JpaRepository<ConfigEntity, Long>, JpaSpecificationExecutor<ConfigEntity> {

    @Query(value = "SELECT value_config FROM config_param "
            + " WHERE name_config = 'max_limit' "
            + " and :date > start_date "
            + " and :date < expire_date ", nativeQuery = true)
    Double getActualLimit(@Param("date") LocalDate date);
}