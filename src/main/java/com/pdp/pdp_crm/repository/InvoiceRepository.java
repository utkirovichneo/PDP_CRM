package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Invoice;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceRepository extends BaseRepository<Invoice, Long> {
}