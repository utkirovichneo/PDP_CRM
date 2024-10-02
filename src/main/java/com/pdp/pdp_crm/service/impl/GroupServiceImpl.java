package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.GroupRepository;
import com.pdp.pdp_crm.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
}
