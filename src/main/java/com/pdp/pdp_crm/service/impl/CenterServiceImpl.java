package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.CenterRepository;
import com.pdp.pdp_crm.service.CenterService;
import org.springframework.stereotype.Service;

@Service
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;

    public CenterServiceImpl(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }
}
