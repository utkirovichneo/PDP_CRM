package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.group.GroupRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupResDTO;
import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.enums.GroupStatus;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.GroupMapper;
import com.pdp.pdp_crm.repository.GroupRepository;
import com.pdp.pdp_crm.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CenterServiceImpl centerServiceImpl;
    private final CourseServiceImpl courseServiceImpl;
    private final MemberServiceImpl memberServiceImpl;
    private final RoomServiceImpl roomServiceImpl;

    @Override
    public GroupDTO findOne(Long centerId, Long id) {
        return groupMapper.toDto(groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new RuntimeException("Group not found")));
    }

    @Override
    public GroupResDTO findGroupResDTO(Long centerId, Long id) {
        return null;
    }

    @Override
    public GroupDTO save(Long centerId, GroupRequestDTO dto) {

        var group = groupRepository.save(Group.builder()
                .groupName(dto.getGroupName())
                .center(centerServiceImpl.findById(centerId).orElseThrow(() -> new RuntimeException("Center not found")))
                .course(courseServiceImpl.findById(centerId, dto.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found")))
                .teacher(memberServiceImpl.findById(centerId, dto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found")))
                .room(roomServiceImpl.findById(centerId, dto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found")))
                .days(dto.getDays())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(dto.getStatus())
                .entityStatus(dto.getEntityStatus())
                .build());

        return groupMapper.toDto(group);
    }

    @Override
    public GroupDTO update(Long centerId, Long id, GroupRequestDTO dto) {
        var group = groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.setCourse(courseServiceImpl.findById(id, dto.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found")));
        group.setTeacher(memberServiceImpl.findById(id, dto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found")));
        group.setRoom(roomServiceImpl.findById(id, dto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found")));
        group.setDays(dto.getDays());
        group.setStartTime(dto.getStartTime());
        group.setEndTime(dto.getEndTime());
        group.setStatus(dto.getStatus());
        group.setEntityStatus(dto.getEntityStatus());
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public Page<GroupDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("center.id", "=", centerId)));
        }
        return groupRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(groupMapper::toDto);
    }


    @Override
    public Optional<Group> findById(Long centerId, Long id) {
        return groupRepository.findByIdAndCenterId(id, centerId);
    }

    @Override
    public Boolean delete(Long centerId, Long id) {
        var group = groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.setStatus(GroupStatus.INACTIVE);
        group.setEntityStatus(EntityStatus.ARCHIVED);
        groupRepository.save(group);
        return Boolean.TRUE;
    }
}