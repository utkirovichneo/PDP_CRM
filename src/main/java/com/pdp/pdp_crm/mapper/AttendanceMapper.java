package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper extends EntityMapper<AttendanceDTO, Attendance> {
    @Override
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "group", ignore = true)
    Attendance toEntity(AttendanceDTO dto);

    @Override
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "groupId", source = "group.id")
    AttendanceDTO toDto(Attendance entity);

    @Override
    List<Attendance> toEntity(List<AttendanceDTO> list);

    @Override
    List<AttendanceDTO> toDto(List<Attendance> list);
}
