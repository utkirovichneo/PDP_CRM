package com.pdp.pdp_crm.dto.collection;

import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.enums.CollectionStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {

    private Long id;

    private LocalDate dueDate;

    private Integer lateDays;

    private BigDecimal amount;

    private StudentDTO student;

    private CollectionStatus status;
}