package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.FinanceIncomeRepository;
import com.pdp.pdp_crm.service.FinanceIncomeService;
import org.springframework.stereotype.Service;

@Service
public class FinanceIncomeServiceImpl implements FinanceIncomeService {
    private final FinanceIncomeRepository financeIncomeRepository;

    public FinanceIncomeServiceImpl(FinanceIncomeRepository financeIncomeRepository) {
        this.financeIncomeRepository = financeIncomeRepository;
    }
}
