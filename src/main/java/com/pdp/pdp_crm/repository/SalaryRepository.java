package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Salary;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends BaseRepository<Salary, Long> {
}