package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.payment.PaymentDTO;
import com.pdp.pdp_crm.dto.payment.PaymentRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

public interface PaymentService {

    Page<PaymentDTO> findAll(Long centerId, PageableRequest pageableRequest);

    PaymentDTO payment(Long centerId, PaymentRequestDTO dto);

}