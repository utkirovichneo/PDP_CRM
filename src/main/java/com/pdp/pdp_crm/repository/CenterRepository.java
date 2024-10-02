package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends BaseRepository<Center, Long> {
}