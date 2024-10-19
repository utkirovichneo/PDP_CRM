package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeRequestDTO;
import com.pdp.pdp_crm.entity.FinanceIncome;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.FinanceIncomeMapper;
import com.pdp.pdp_crm.repository.FinanceIncomeRepository;
import com.pdp.pdp_crm.repository.FinanceRepository;
import com.pdp.pdp_crm.service.FinanceIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceIncomeServiceImpl implements FinanceIncomeService {

    private final FinanceIncomeRepository financeIncomeRepository;
    private final FinanceIncomeMapper financeIncomeMapper;
    private final FinanceServiceImpl financeServiceImpl;

    @Override
    public Page<FinanceIncomeDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("finance.center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(List.of(new SearchCriteria("finance.center.id", "=", centerId)));
        }
        return financeIncomeRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(financeIncomeMapper::toDto);
    }

    @Override
    public FinanceIncomeDTO findOne(Long centerId, Long financeIncomeId) {
        return financeIncomeMapper.toDto(financeIncomeRepository
                .findByIdAndFinanceCenterId(financeIncomeId, centerId)
                .orElseThrow(() -> new NotFoundException("Finance")));
    }

    @Override
    public FinanceIncomeDTO makingMoney(Long centerId, FinanceIncomeRequestDTO dto) {

        FinanceIncomeDTO income = financeIncomeMapper.toDto(financeIncomeRepository.save(FinanceIncome.builder()
                .finance(financeServiceImpl.findByCenterId(centerId).orElseThrow(() -> new NotFoundException("Finance")))
                .amount(dto.getAmount())
                .transactionDate(dto.getTransactionDate())
                .status(dto.getStatus())
                .build()));

        financeServiceImpl.calculateIncome(centerId, income);

        return income;

    }
}
