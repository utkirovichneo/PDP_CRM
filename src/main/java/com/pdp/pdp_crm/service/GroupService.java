package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.group.GroupRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupResDTO;
import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface GroupService {

    GroupDTO findOne(Long centerId, Long id);

    GroupResDTO findGroupResDTO(Long centerId, Long id);

    GroupDTO save(Long centerId, GroupRequestDTO dto);

    Optional<Group> findById(Long centerId, Long id);

    Page<GroupDTO> findAll(Long centerId, PageableRequest pageableRequest);

    GroupDTO update(Long centerId, Long id, GroupRequestDTO dto);

    Boolean delete(Long centerId, Long id);
}