package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.GroupRepository;
import com.pdp.pdp_crm.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
}
