package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.entity.Center;

public interface CenterService {

    CenterDTO create(CenterRequestDTO dto);

    Center findById(Long id);
    CenterDTO update(Long id, CenterRequestDTO dto);
}