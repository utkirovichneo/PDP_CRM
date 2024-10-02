package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.FinanceOutcomeRepository;
import com.pdp.pdp_crm.service.FinanceOutcomeService;
import org.springframework.stereotype.Service;

@Service
public class FinanceOutcomeServiceImpl implements FinanceOutcomeService {
    private final FinanceOutcomeRepository financeOutcomeRepository;

    public FinanceOutcomeServiceImpl(FinanceOutcomeRepository financeOutcomeRepository) {
        this.financeOutcomeRepository = financeOutcomeRepository;
    }
}
