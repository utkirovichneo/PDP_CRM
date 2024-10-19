package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

public interface FinanceIncomeService {

    Page<FinanceIncomeDTO> findAll(Long centerId, PageableRequest pageableRequest);

    FinanceIncomeDTO findOne(Long centerId, Long financeIncomeId);

    FinanceIncomeDTO makingMoney(Long centerId, FinanceIncomeRequestDTO dto);

}