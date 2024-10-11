package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.group.GroupRequestDTO;
import com.pdp.pdp_crm.entity.Group;

import java.util.Optional;

public interface GroupService {

    GroupDTO create(GroupRequestDTO dto);

    GroupDTO findById(Long id);

    GroupDTO update(Long id, GroupRequestDTO dto);

    Optional<Group> findByIdOptional(Long id);

}