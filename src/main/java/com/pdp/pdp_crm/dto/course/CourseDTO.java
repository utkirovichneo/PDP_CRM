package com.pdp.pdp_crm.dto.course;

import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.enums.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;

    private CenterDTO center;

    private String name;

    private String description;

    private BigDecimal price;

    private Long duration;

    private Long countOfLessons;

    private CourseStatus status;
}