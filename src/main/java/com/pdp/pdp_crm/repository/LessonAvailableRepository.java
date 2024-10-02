package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.LessonAvailable;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonAvailableRepository extends BaseRepository<LessonAvailable, Long> {
}