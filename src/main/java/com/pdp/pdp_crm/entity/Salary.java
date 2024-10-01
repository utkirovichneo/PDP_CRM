package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Salary extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;          // kimga tashlanishi

    @Column(nullable = false)
    private BigDecimal salary;      // qancha tashlanishi

    @Column(nullable = false)
    private LocalDate paymentDate;  // qaysi sanada tashlanganligi(teacherlarga har gurux moduli tugaganda tushadi, qolganlaga oyni boshida tushadi)

}