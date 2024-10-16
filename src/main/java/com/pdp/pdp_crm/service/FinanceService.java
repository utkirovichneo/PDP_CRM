package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.finance.FinanceDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.entity.Finance;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface FinanceService {

    Boolean createFinance(Center center);

    Optional<Finance> findByCenterId(Long centerId);

    FinanceDTO getOne(Long centerId);

    Page<FinanceIncomeDTO> findAllIncomeFinance(Long centerId, PageableRequest pageableRequest);

    FinanceIncomeDTO findOneIncomeFinance(Long centerId, Long incomeId);

    Page<FinanceOutcomeDTO> findAllOutcomeFinance(Long centerId, PageableRequest pageableRequest);

    FinanceOutcomeDTO findOneOutcomeFinance(Long centerId, Long outcomeId);
}