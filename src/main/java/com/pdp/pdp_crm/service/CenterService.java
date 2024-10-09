package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;

public interface CenterService {

    CenterDTO save(CenterRequestDTO dto);

    CenterDTO findById(Long id);



}