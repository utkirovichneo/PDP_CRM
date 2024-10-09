package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.repository.CenterRepository;
import com.pdp.pdp_crm.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;

    @Override
    public CenterDTO save(CenterRequestDTO dto) {
        return null;
    }

    @Override
    public CenterDTO findById(Long id) {
        return null;
    }
}
