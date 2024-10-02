package com.pdp.pdp_crm.dto.financeoutcome;

import com.pdp.pdp_crm.enums.FinanceStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceOutcomeRequestDTO {

    private BigDecimal amount;

    private LocalDate transactionDate;

    private FinanceStatus status;

}
