package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
    @Override
    @Mapping(target = "group", ignore = true)
    Student toEntity(StudentDTO dto);

    @Override
    @Mapping(source = "group.id", target = "groupId")
    StudentDTO toDto(Student entity);

    @Override
    List<Student> toEntity(List<StudentDTO> list);

    @Override
    List<StudentDTO> toDto(List<Student> list);
}
