package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.course.CourseDTO;
import com.pdp.pdp_crm.dto.course.CourseRequestDTO;
import com.pdp.pdp_crm.entity.Course;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CourseService {

    CourseDTO save(Long centerId, CourseRequestDTO dto);

    CourseDTO findOne(Long centerId, Long id);

    Page<CourseDTO> findAll(Long centerId, PageableRequest pageableRequest);

    CourseDTO update(Long centerId, Long id, CourseRequestDTO dto);

    Boolean delete(Long centerId, Long id);

    Optional<Course> findById(Long centerId, Long id);

}