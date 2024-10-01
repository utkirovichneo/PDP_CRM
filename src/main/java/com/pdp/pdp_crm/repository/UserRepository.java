package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.User;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findUserByPhoneNumber(String username);
}