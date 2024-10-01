package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import com.pdp.pdp_crm.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Course extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    /*Bu nechta modulligi*/
    @Column(nullable = false)
    private Long duration;

    /*Bu bir modulda nechta dars borligi*/
    @Column(nullable = false)
    private Long countOfLessons;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

}