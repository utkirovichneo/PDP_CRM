package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import com.pdp.pdp_crm.enums.PaymentStatus;
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
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Payment extends BaseEntity {

    private BigDecimal amount;  // qancha to'lov qilinganini bildiradi

    private LocalDate paidDate; // to'lov qilingan sana

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;        // online, naqd yoki kartadan to'langan

    private String description;     // to'lov haqida kamentariya yozish mumkin

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;  // bu to'lov talabi, agar to'lov talabi yaratilmasa to'lov qilinmaydi
}