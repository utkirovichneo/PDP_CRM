package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.RoleRepository;
import com.pdp.pdp_crm.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

}
