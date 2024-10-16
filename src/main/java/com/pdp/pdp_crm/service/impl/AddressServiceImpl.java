package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.address.AddressDTO;
import com.pdp.pdp_crm.dto.address.AddressRequestDTO;
import com.pdp.pdp_crm.entity.Address;
import com.pdp.pdp_crm.mapper.AddressMapper;
import com.pdp.pdp_crm.repository.AddressRepository;
import com.pdp.pdp_crm.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public AddressDTO save(AddressRequestDTO dto) {

        var address = addressRepository.save(Address.builder()
                        .city(dto.getCity())
                        .street(dto.getStreet())
                        .number(dto.getNumber())
                        .description(dto.getDescription())
                        .build());

        return addressMapper.toDto(address);
    }
}