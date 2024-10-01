package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import com.pdp.pdp_crm.enums.FinanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Finance extends BaseEntity {

    private BigDecimal income;           // kirim pullari

    private BigDecimal expenses;         // chiqim pullari

    private BigDecimal netBalance;       // daromat va xarajat o'rtasidagi farq

    private LocalDate transactionDate;   // Operatsiya amalga oshgan sana

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinanceStatus status;        // bu kirim yoki chiqim nima uchun bo'lganini ko'rsatadi, ikkinchi versiyada ko'proq tanlov bo'ladi
}