package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.CourseRepository;
import com.pdp.pdp_crm.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
