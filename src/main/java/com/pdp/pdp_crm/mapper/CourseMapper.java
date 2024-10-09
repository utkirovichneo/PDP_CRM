package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.course.CourseDTO;
import com.pdp.pdp_crm.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CenterMapper.class})
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {
    @Override
    Course toEntity(CourseDTO dto);

    @Override
    CourseDTO toDto(Course entity);

    @Override
    List<Course> toEntity(List<CourseDTO> list);

    @Override
    List<CourseDTO> toDto(List<Course> list);
}
