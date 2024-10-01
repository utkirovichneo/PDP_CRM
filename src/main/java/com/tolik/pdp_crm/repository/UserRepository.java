package com.tolik.pdp_crm.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.initial_configuration_in_spring_boot_project.entity.User;
import uz.pdp.initial_configuration_in_spring_boot_project.repository.baserepository.BaseRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findUserByPhoneNumber(String username);
}