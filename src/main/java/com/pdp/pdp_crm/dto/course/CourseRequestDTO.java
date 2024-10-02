package com.pdp.pdp_crm.dto.course;

import com.pdp.pdp_crm.enums.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    private Long centerId;

    private String name;

    private String description;

    private BigDecimal price;

    private Long duration;

    private Long countOfLessons;

    private CourseStatus status;
}