package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.group.GroupRequestDTO;
import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.enums.GroupStatus;
import com.pdp.pdp_crm.mapper.GroupMapper;
import com.pdp.pdp_crm.repository.GroupRepository;
import com.pdp.pdp_crm.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CenterServiceImpl centerServiceImpl;
    private final CourseServiceImpl courseServiceImpl;
    private final TeacherServiceImpl teacherServiceImpl;
    private final RoomServiceImpl roomServiceImpl;

    @Override
    public GroupDTO create(GroupRequestDTO dto) {
        return groupMapper.toDto(
                groupRepository.save(
                        Group.builder()
                                .groupName(dto.getGroupName())
                                .course(courseServiceImpl.findByIdOptional(dto.getCourseId()).orElseThrow(RuntimeException::new))
//                                .teacher( teacherServiceImpl.findById(dto.getTeacherId()))
                                .room(roomServiceImpl.findByIdOptional(dto.getRoomId()).orElseThrow(RuntimeException::new))
                                .days(List.of())
                                .startTime(dto.getStartTime())
                                .endTime(dto.getEndTime())
                                .status(GroupStatus.ACTIVE)
                                .build()
        )
        );
    }

    @Override
    public GroupDTO findById(Long id) {
        return groupMapper.toDto(groupRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public GroupDTO update(Long id, GroupRequestDTO dto) {
        Group group = groupRepository.findById(id).orElseThrow(RuntimeException::new);
        group.setGroupName(dto.getGroupName());
        group.setCourse(courseServiceImpl.findByIdOptional(dto.getCourseId()).orElseThrow(RuntimeException::new));
        group.setRoom(roomServiceImpl.findByIdOptional(dto.getRoomId()).orElse(null));
        group.setDays(dto.getDays());
        group.setStartTime(dto.getStartTime());
        group.setEndTime(dto.getEndTime());
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public Optional<Group> findByIdOptional(Long id) {
        return groupRepository.findById(id);
    }
}
