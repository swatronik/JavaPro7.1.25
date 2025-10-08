package org.example.payment_limit.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "config_param")
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameConfig;

    private String valueConfig;

    private LocalDate startDate;

    private LocalDate expireDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameConfig() {
        return nameConfig;
    }

    public void setNameConfig(String nameConfig) {
        this.nameConfig = nameConfig;
    }

    public String getValueConfig() {
        return valueConfig;
    }

    public void setValueConfig(String valueConfig) {
        this.valueConfig = valueConfig;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "ConfigEntity{" +
                "id=" + id +
                ", nameConfig='" + nameConfig + '\'' +
                ", valueConfig='" + valueConfig + '\'' +
                ", startDate='" + startDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                '}';
    }
}