package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class LessonAvailable extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(nullable = false)
    private LocalDate date;

    private Boolean isLessonAvailable; // Bunda true bo'lsa dars o'tilgan bo'ladi, False bo'lsa dars o'tilmagan bo'ladi
}