package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.invoice.InvoiceDTO;
import com.pdp.pdp_crm.entity.Invoice;
import com.pdp.pdp_crm.enums.InvoiceStatus;
import com.pdp.pdp_crm.mapper.InvoiceMapper;
import com.pdp.pdp_crm.repository.InvoiceRepository;
import com.pdp.pdp_crm.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    @Lazy
    public void setInvoiceServiceImpl(CollectionServiceImpl collectionServiceImpl) {
        this.collectionServiceImpl = collectionServiceImpl;
    }
    private  CollectionServiceImpl collectionServiceImpl;

    @Override
    public InvoiceDTO createInvoice(Long centerId, Long collectionId) {

        Invoice invoice = invoiceRepository.save(
                Invoice.builder()
                        .invoiceNumber("")
                        .collection(collectionServiceImpl.findById(collectionId).orElseThrow(() -> new RuntimeException("Collection not found")))
                        .issueDate(LocalDate.now())
                        .issueDate(LocalDate.now().plusDays(3))
                        .status(InvoiceStatus.NEW)
                        .build()
        );

        invoice.setInvoiceNumber(String.format("INV-A%07d", invoice.getId()));

        return invoiceMapper.toDto(invoiceRepository.save(invoice));
    }
}