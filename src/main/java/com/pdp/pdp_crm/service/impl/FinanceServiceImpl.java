package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.finance.FinanceDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.entity.Finance;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.mapper.FinanceMapper;
import com.pdp.pdp_crm.repository.FinanceRepository;
import com.pdp.pdp_crm.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private FinanceRepository repository;
    private final FinanceMapper financeMapper;
    private final FinanceIncomeServiceImpl financeIncomeService;
    private final FinanceOutcomeServiceImpl financeOutcomeService;

    @Override
    public Boolean createFinance(Center center) {
        repository.save(
                Finance.builder()
                        .center(center)
                        .balance(BigDecimal.ZERO)
                        .build());
        return Boolean.TRUE;
    }

    @Override
    public Optional<Finance> findByCenterId(Long centerId) {
        return repository.findByCenterId(centerId);
    }

    @Override
    public FinanceDTO getOne(Long centerId) {
        return financeMapper.toDto(repository.findByCenterId(centerId).orElseThrow(() -> new NotFoundException("Finance")));
    }

    @Override
    public Page<FinanceIncomeDTO> findAllIncomeFinance(Long centerId, PageableRequest pageableRequest) {
        return financeIncomeService.findAll(centerId, pageableRequest);
    }

    @Override
    public FinanceIncomeDTO findOneIncomeFinance(Long centerId, Long incomeId) {
        return financeIncomeService.findOne(centerId, incomeId);
    }

    @Override
    public Page<FinanceOutcomeDTO> findAllOutcomeFinance(Long centerId, PageableRequest pageableRequest) {
        return financeOutcomeService.findAll(centerId, pageableRequest);
    }

    @Override
    public FinanceOutcomeDTO findOneOutcomeFinance(Long centerId, Long outcomeId) {
        return financeOutcomeService.findById(centerId, outcomeId);
    }
}
