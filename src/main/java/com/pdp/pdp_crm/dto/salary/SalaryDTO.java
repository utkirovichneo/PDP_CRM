package com.pdp.pdp_crm.dto.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {

    private Long id;

    private Long memberId;

    private BigDecimal salary;

    private LocalDate paymentDate;
}