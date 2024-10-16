package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.address.AddressDTO;
import com.pdp.pdp_crm.dto.address.AddressRequestDTO;
import com.pdp.pdp_crm.entity.Address;

import java.util.Optional;

public interface AddressService {

    Optional<Address> findById(Long id);

    AddressDTO save(AddressRequestDTO dto);

}