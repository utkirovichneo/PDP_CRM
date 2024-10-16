package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.FinanceOutcome;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceOutcomeRepository extends BaseRepository<FinanceOutcome, Long> {

    Optional<FinanceOutcome> findByIdAndFinanceCenterId(Long id, Long financeCenterId);

}