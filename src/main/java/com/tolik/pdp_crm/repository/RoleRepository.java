package com.tolik.pdp_crm.repository;

import com.tolik.pdp_crm.entity.Role;
import com.tolik.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

}