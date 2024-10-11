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
    public AddressDTO create(AddressRequestDTO dto) {
        return addressMapper.toDto(
                addressRepository.save(
                        Address.builder()
                .city(dto.getCity())
                .street(dto.getStreet())
                .number(dto.getNumber())
                .description(dto.getDescription())
                .build()
            )
        );
    }

    @Override
    public AddressDTO findById(Long id) {
        return addressMapper.toDto(addressRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public AddressDTO update(Long id, AddressRequestDTO dto) {
        Address address = addressRepository.findById(id).orElseThrow(RuntimeException::new);
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setDescription(dto.getDescription());
        return addressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public Optional<Address> findByIdOptional(Long id) {
        return addressRepository.findById(id);
    }
}