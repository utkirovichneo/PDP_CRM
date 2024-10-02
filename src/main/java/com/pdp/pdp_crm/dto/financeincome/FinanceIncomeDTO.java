package com.pdp.pdp_crm.dto.financeincome;

import com.pdp.pdp_crm.enums.FinanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceIncomeDTO {

    private Long id;

    private Long financeId;

    private BigDecimal amount;

    private LocalDate transactionDate;

    private FinanceStatus status;
}
