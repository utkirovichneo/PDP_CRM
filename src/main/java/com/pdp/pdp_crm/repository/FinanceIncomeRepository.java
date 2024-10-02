package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.FinanceIncome;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceIncomeRepository extends BaseRepository<FinanceIncome, Long> {
}