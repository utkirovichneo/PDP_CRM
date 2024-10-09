package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.SalaryRepository;
import com.pdp.pdp_crm.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;

}
