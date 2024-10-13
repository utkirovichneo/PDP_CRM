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
public class Finance extends BaseEntity {

    private BigDecimal balance;       // daromat va xarajat o'rtasidagi farq

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "center_id")
    private Center center;

}