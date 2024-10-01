package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import com.pdp.pdp_crm.enums.CollectionStatus;
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
public class Collection extends BaseEntity {

    private LocalDate dueDate;  // to'lov qilinishi kerak bo'lgan sana

    private Integer lateDays;   // kechiktirilgan kunlar

    private BigDecimal amount;  // to'lov qilinishi kerak bo'lgan summa

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private CollectionStatus status;        // to'lov qilingan yoki qilinmaganini ko'rsatadi

}