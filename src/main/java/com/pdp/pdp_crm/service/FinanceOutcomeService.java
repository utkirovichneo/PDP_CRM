package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

public interface FinanceOutcomeService {

    Page<FinanceOutcomeDTO> findAll(Long centerId, PageableRequest pageableRequest);

    FinanceOutcomeDTO findById(Long centerId, Long financeOutcomeId);

}