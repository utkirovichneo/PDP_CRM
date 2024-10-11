package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.exception.CenterNotFoundException;
import com.pdp.pdp_crm.mapper.CenterMapper;
import com.pdp.pdp_crm.mapper.UserMapperImpl;
import com.pdp.pdp_crm.repository.AddressRepository;
import com.pdp.pdp_crm.repository.CenterRepository;
import com.pdp.pdp_crm.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;
    private final CenterMapper centerMapper;

    private final UserMapperImpl userMapperImpl;
    private final AddressServiceImpl addressServiceImpl;


    @Override
    public CenterDTO create(CenterRequestDTO dto) {
        return centerMapper.toDto(
                centerRepository.save(
                        Center.builder()
                                .name(dto.getName())
                                .legalName(dto.getLegalName())
//                                .address(addressServiceImpl.findByIdOptional(dto.getAddress()))  // Assuming AddressRepository has save method to save Address entity
                                .phone(dto.getPhone())
                                .email(dto.getEmail())
                                .description(dto.getDescription())
//                                .user()
                                .build()
                )
        );
    }

    @Override
    public Center findById(Long centerId) {
        return centerRepository.findById(centerId).orElseThrow(CenterNotFoundException::new);
    }

    @Override
    public CenterDTO update(Long id, CenterRequestDTO dto) {
        return null;
    }
}
