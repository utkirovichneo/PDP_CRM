package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.CourseRepository;
import com.pdp.pdp_crm.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

}
