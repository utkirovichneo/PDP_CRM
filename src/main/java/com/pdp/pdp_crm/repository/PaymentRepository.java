package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Payment;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends BaseRepository<Payment, Long> {
}