package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import com.pdp.pdp_crm.enums.FinanceStatus;
import jakarta.persistence.*;
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
public class FinanceIncome extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;

    private BigDecimal amount;

    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private FinanceStatus status;
}
