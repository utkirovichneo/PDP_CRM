package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Course;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course, Long> {
}