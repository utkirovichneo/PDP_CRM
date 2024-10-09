package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper extends EntityMapper<GroupDTO, Group> {
    @Override
    @Mapping(target = "center", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    Group toEntity(GroupDTO dto);

    @Override
    @Mapping(source = "center.id", target = "centerId")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "room.id", target = "roomId")
    GroupDTO toDto(Group entity);

    @Override
    List<Group> toEntity(List<GroupDTO> list);

    @Override
    List<GroupDTO> toDto(List<Group> list);
}
