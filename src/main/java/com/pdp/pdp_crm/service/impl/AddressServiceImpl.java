package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.AddressRepository;
import com.pdp.pdp_crm.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

}