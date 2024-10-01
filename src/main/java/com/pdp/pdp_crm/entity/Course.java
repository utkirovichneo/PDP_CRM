package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import com.pdp.pdp_crm.enums.CourseStatus;
import jakarta.persistence.Entity;
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

    private Center center;

    private String name;

    private String description;

    private BigDecimal price;

    /*Bu nechta modulligi*/
    private Long duration;

    /*Bu bir modulda nechta dars borligi*/
    private Long countOfLessons;

    private CourseStatus status;

}