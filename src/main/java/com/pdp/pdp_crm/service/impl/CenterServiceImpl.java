package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.mapper.CenterMapper;
import com.pdp.pdp_crm.repository.CenterRepository;
import com.pdp.pdp_crm.service.AddressService;
import com.pdp.pdp_crm.service.CenterAuthService;
import com.pdp.pdp_crm.service.CenterService;
import com.pdp.pdp_crm.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository repository;
    private final CenterMapper centerMapper;
    private final FinanceService financeServiceImpl;
    private final AddressService addressServiceImpl;

    @Autowired
    @Lazy
    public void setCenterService(CenterAuthService centerAuthService) {
        this.centerAuthServiceImpl = centerAuthService;
    }
    private CenterAuthService centerAuthServiceImpl;

    @Override
    public CenterDTO save(CenterRequestDTO dto) {

        var address = addressServiceImpl.save(dto.getAddress());

        var center = repository.save(Center.builder()
                .name(dto.getName())
                .legalName(dto.getLegalName())
                .address(addressServiceImpl.findById(address.getId()).orElseThrow(()-> new NotFoundException("Address")))
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .description(dto.getDescription())
                .user(centerAuthServiceImpl.findById(dto.getUserId()).orElseThrow(()-> new NotFoundException("User")))
                .build());

            financeServiceImpl.createFinance(center);

            return centerMapper.toDto(center);
    }

    @Override
    public Optional<Center> findById(Long id) {
        return repository.findById(id);
    }
}