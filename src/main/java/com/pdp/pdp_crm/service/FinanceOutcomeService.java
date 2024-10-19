package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

public interface FinanceOutcomeService {

    FinanceOutcomeDTO spendingMoney(Long centerId, FinanceOutcomeRequestDTO dto);

    Page<FinanceOutcomeDTO> findAll(Long centerId, PageableRequest pageableRequest);

    FinanceOutcomeDTO findById(Long centerId, Long financeOutcomeId);

}