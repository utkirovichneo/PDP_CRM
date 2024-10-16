package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.entity.Center;

import java.util.Optional;

public interface CenterService {

    CenterDTO save(CenterRequestDTO dto);

    Optional<Center> findById(Long id);

}