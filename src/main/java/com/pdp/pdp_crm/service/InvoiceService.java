package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.invoice.InvoiceDTO;

public interface InvoiceService {

    InvoiceDTO createInvoice(Long centerId, Long collectionId);

}