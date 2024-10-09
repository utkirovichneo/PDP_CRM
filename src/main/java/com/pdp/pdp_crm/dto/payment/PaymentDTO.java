package com.pdp.pdp_crm.dto.payment;

import com.pdp.pdp_crm.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    private BigDecimal amount;

    private LocalDate paidDate;

    private PaymentStatus paymentStatus;

    private String description;

    private Long collectionId;
}
