package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Role;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

}