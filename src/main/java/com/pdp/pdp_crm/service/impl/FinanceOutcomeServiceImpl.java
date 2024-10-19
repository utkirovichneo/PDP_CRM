package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeRequestDTO;
import com.pdp.pdp_crm.entity.Finance;
import com.pdp.pdp_crm.entity.FinanceOutcome;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.FinanceOutcomeMapper;
import com.pdp.pdp_crm.mapper.FinanceOutcomeMapperImpl;
import com.pdp.pdp_crm.repository.FinanceOutcomeRepository;
import com.pdp.pdp_crm.service.FinanceOutcomeService;
import com.pdp.pdp_crm.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceOutcomeServiceImpl implements FinanceOutcomeService {

    private final FinanceOutcomeRepository financeOutcomeRepository;
    private final FinanceOutcomeMapperImpl financeOutcomeMapperImpl;
    private final FinanceOutcomeMapper financeOutcomeMapper;
    private final FinanceService financeService;

    @Override
    public FinanceOutcomeDTO spendingMoney(Long centerId, FinanceOutcomeRequestDTO dto) {

        var finance = financeService.findByCenterId(centerId).orElseThrow(() -> new NotFoundException("Finance"));

        return financeOutcomeMapper.toDto(financeOutcomeRepository.save(FinanceOutcome.builder()
                .finance(finance)
                .amount(dto.getAmount())
                .transactionDate(dto.getTransactionDate())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build()));
    }

    @Override
    public Page<FinanceOutcomeDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("finance.center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(List.of(new SearchCriteria("finance.center.id", "=", centerId)));
        }
        return financeOutcomeRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(financeOutcomeMapperImpl::toDto);
    }

    @Override
    public FinanceOutcomeDTO findById(Long centerId, Long financeOutcomeId) {
        return financeOutcomeMapper.toDto(
                financeOutcomeRepository.findByIdAndFinanceCenterId(financeOutcomeId, centerId)
                        .orElseThrow(() -> new NotFoundException("FinanceOutcome")));
    }
}
