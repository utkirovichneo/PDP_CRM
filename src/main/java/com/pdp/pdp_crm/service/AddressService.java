package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.address.AddressDTO;
import com.pdp.pdp_crm.dto.address.AddressRequestDTO;
import com.pdp.pdp_crm.entity.Address;

import java.util.Optional;

public interface AddressService {

    AddressDTO create(AddressRequestDTO dto);

    AddressDTO findById(Long id);

    AddressDTO update(Long id, AddressRequestDTO dto);
    Optional<Address>findByIdOptional(Long id);

}