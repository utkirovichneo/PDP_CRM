package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.FinanceRepository;
import com.pdp.pdp_crm.service.FinanceService;
import org.springframework.stereotype.Service;

@Service
public class FinanceServiceImpl implements FinanceService {
    private final FinanceRepository financeRepository;

    public FinanceServiceImpl(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }
}
