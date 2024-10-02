package com.pdp.pdp_crm.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceDTO {

    private Long id;

    private BigDecimal balance;

}
