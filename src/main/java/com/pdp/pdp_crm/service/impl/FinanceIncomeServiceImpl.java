package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.FinanceIncomeRepository;
import com.pdp.pdp_crm.service.FinanceIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceIncomeServiceImpl implements FinanceIncomeService {
    private final FinanceIncomeRepository financeIncomeRepository;

}
