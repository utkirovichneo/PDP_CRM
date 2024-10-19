package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.LessonAvailable;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LessonAvailableRepository extends BaseRepository<LessonAvailable, Long> {

    Optional<LessonAvailable> findByGroupIdAndGroupTeacherId(Long groupId, Long teacherId);

}