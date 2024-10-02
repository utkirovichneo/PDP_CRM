package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.PaymentRepository;
import com.pdp.pdp_crm.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
