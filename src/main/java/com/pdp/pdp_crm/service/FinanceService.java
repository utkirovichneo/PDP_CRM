package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.entity.Finance;

import java.util.Optional;

public interface FinanceService {

    Boolean createFinance(Center center);

    Optional<Finance> findByCenterId(Long centerId);

}