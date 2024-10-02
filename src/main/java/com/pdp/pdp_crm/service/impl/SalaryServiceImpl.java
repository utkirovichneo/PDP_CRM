package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.SalaryRepository;
import com.pdp.pdp_crm.service.SalaryService;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }
}
