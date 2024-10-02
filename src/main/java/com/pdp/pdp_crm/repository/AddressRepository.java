package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Address;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {
}