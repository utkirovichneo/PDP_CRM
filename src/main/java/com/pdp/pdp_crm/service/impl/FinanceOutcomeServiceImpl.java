package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.FinanceOutcomeRepository;
import com.pdp.pdp_crm.service.FinanceOutcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceOutcomeServiceImpl implements FinanceOutcomeService {
    private final FinanceOutcomeRepository financeOutcomeRepository;

}
