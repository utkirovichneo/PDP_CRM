package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends BaseRepository<Group, Long> {
}