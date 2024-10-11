package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.salary.SalaryDTO;
import com.pdp.pdp_crm.dto.salary.SalaryRequestDTO;
import com.pdp.pdp_crm.entity.Salary;
import com.pdp.pdp_crm.mapper.SalaryMapper;
import com.pdp.pdp_crm.repository.SalaryRepository;
import com.pdp.pdp_crm.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final SalaryMapper salaryMapper;
    private final MemberServiceImpl memberServiceImpl;

    @Override
    public SalaryDTO create(SalaryRequestDTO dto) {
        return salaryMapper.toDto(
                salaryRepository.save(
                        Salary.builder()
                                .member(memberServiceImpl.findByIdOptional(dto.getMemberId()).orElseThrow(RuntimeException::new))
                                .salary(dto.getSalary())
                                .paymentDate(dto.getPaymentDate())
                                .build()
                )
        );
    }

    @Override
    public SalaryDTO findById(Long id) {
        return salaryMapper.toDto(salaryRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public SalaryDTO update(Long id, SalaryRequestDTO dto) {
        Salary salary = salaryRepository.findById(id).orElseThrow(RuntimeException::new);
        salary.setSalary(dto.getSalary());
        salary.setPaymentDate(dto.getPaymentDate());
        return salaryMapper.toDto(salaryRepository.save(salary));
    }
}
