package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

public interface LessonAvailableService {

    LessonAvailableDTO save(Long teacherId, LessonAvailableRequestDTO dto);

    LessonAvailableDTO confirm(Long teacherId, Long groupId, Long id);

    Page<LessonAvailableDTO> findAll(Long teacherId, PageableRequest pageableRequest);

}