package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.entity.Finance;
import com.pdp.pdp_crm.repository.FinanceRepository;
import com.pdp.pdp_crm.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private FinanceRepository repository;

    @Override
    public Boolean createFinance(Center center) {
        repository.save(
                Finance.builder()
                        .center(center)
                        .balance(BigDecimal.ZERO)
                        .build());
        return Boolean.TRUE;
    }

    @Override
    public Optional<Finance> findByCenterId(Long centerId) {
        return repository.findByCenterId(centerId);
    }
}
