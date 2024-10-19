package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.finance.FinanceDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeRequestDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeRequestDTO;
import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.entity.Finance;
import com.pdp.pdp_crm.entity.FinanceIncome;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.mapper.FinanceMapper;
import com.pdp.pdp_crm.repository.FinanceRepository;
import com.pdp.pdp_crm.service.FinanceIncomeService;
import com.pdp.pdp_crm.service.FinanceOutcomeService;
import com.pdp.pdp_crm.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository repository;
    private final FinanceMapper financeMapper;
    @Autowired
    @Lazy
    public void setFinanceServiceImpl(FinanceIncomeService financeIncomeService, FinanceOutcomeService financeOutcomeService) {
        this.financeIncomeService = financeIncomeService;
        this.financeOutcomeService = financeOutcomeService;
    }
    private FinanceIncomeService financeIncomeService;
    private FinanceOutcomeService financeOutcomeService;


    @Override
    public void createFinance(Center center) {
        repository.save(
                Finance.builder()
                        .center(center)
                        .balance(BigDecimal.ZERO)
                        .build());
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

    @Override
    public FinanceOutcomeDTO ownerExpense(Long centerId, FinanceOutcomeRequestDTO dto) {
        FinanceOutcomeDTO financeOutcomeDTO = financeOutcomeService.spendingMoney(centerId, dto);
        var finance = repository.findByCenterId(centerId).orElseThrow(() -> new NotFoundException("Finance"));
        finance.setBalance(finance.getBalance().subtract(dto.getAmount()));
        repository.save(finance);
        return financeOutcomeDTO;
    }

    @Override
    public void calculateIncome(Long centerId, FinanceIncomeDTO dto) {

        repository.findByCenterId(centerId).ifPresent(finance -> {
            finance.setBalance(finance.getBalance().add(dto.getAmount()));
            repository.save(finance);
        });
    }
}
