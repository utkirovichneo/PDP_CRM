package com.tolik.pdp_crm.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.initial_configuration_in_spring_boot_project.entity.Role;
import uz.pdp.initial_configuration_in_spring_boot_project.repository.baserepository.BaseRepository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

}