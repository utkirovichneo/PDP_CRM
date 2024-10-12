package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.salary.SalaryDTO;
import com.pdp.pdp_crm.dto.salary.SalaryRequestDTO;

public interface SalaryService {

    SalaryDTO create(SalaryRequestDTO dto);

    SalaryDTO findById(Long id);

    SalaryDTO update(Long id, SalaryRequestDTO dto);

}