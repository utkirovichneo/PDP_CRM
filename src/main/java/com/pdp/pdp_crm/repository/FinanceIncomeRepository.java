package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.FinanceIncome;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceIncomeRepository extends BaseRepository<FinanceIncome, Long> {

    Optional<FinanceIncome> findByIdAndFinanceCenterId(Long id, Long centerId);

}