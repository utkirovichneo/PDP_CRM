package com.pdp.pdp_crm.dto.invoice;

import com.pdp.pdp_crm.enums.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Long id;

    private String invoiceNumber;

    private Long collectionId;

    private BigDecimal amount;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private InvoiceStatus status;
}