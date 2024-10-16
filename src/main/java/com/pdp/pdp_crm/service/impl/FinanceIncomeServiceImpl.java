package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.FinanceIncomeMapper;
import com.pdp.pdp_crm.repository.FinanceIncomeRepository;
import com.pdp.pdp_crm.service.FinanceIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class FinanceIncomeServiceImpl implements FinanceIncomeService {

    private final FinanceIncomeRepository financeIncomeRepository;
    private final FinanceIncomeMapper financeIncomeMapper;

    @Override
    public Page<FinanceIncomeDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("finance.center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("finance.center.id", "=", centerId)));
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
                .orElseThrow(() -> new RuntimeException("Finance not found")));
    }
}
