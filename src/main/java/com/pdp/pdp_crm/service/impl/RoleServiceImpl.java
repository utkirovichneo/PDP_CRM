package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.RoleRepository;
import com.pdp.pdp_crm.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
