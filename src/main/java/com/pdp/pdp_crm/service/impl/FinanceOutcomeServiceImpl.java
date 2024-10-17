package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.FinanceOutcomeMapper;
import com.pdp.pdp_crm.mapper.FinanceOutcomeMapperImpl;
import com.pdp.pdp_crm.repository.FinanceOutcomeRepository;
import com.pdp.pdp_crm.service.FinanceOutcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class FinanceOutcomeServiceImpl implements FinanceOutcomeService {

    private final FinanceOutcomeRepository financeOutcomeRepository;
    private final FinanceOutcomeMapperImpl financeOutcomeMapperImpl;
    private final FinanceOutcomeMapper financeOutcomeMapper;

    @Override
    public Page<FinanceOutcomeDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("finance.center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("finance.center.id", "=", centerId)));
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
