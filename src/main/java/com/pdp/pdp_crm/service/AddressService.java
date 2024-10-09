package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.address.AddressDTO;
import com.pdp.pdp_crm.dto.address.AddressRequestDTO;

public interface AddressService {

    AddressDTO create(AddressRequestDTO dto);

    AddressDTO findById(Long id);

    AddressDTO update(Long id, AddressRequestDTO dto);

}