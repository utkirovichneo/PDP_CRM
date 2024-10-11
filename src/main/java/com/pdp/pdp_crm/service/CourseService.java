package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.course.CourseDTO;
import com.pdp.pdp_crm.dto.course.CourseRequestDTO;
import com.pdp.pdp_crm.entity.Course;

import java.util.Optional;

public interface CourseService {

    CourseDTO create(CourseRequestDTO dto);

    CourseDTO findById(Long id);
    Optional<Course> findByIdOptional(Long id);

    CourseDTO update(Long id, CourseRequestDTO dto);
}