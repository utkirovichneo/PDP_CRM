package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.InvoiceRepository;
import com.pdp.pdp_crm.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

}
