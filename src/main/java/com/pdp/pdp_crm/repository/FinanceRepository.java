package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Finance;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceRepository extends BaseRepository<Finance, Long> {

    Optional<Finance> findByCenterId(Long id);
}