package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.AddressRepository;
import com.pdp.pdp_crm.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


}