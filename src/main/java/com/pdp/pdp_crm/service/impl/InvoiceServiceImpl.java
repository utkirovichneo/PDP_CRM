package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.InvoiceRepository;
import com.pdp.pdp_crm.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
