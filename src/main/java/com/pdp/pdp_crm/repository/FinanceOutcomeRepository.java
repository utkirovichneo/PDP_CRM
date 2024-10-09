package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.FinanceOutcome;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceOutcomeRepository extends BaseRepository<FinanceOutcome, Long> {
}