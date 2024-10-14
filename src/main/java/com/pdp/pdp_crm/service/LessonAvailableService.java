package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;

public interface LessonAvailableService {

    LessonAvailableDTO save(Long teacherId, LessonAvailableRequestDTO dto);

    LessonAvailableDTO confirm(Long teacherId, LessonAvailableDTO dto);

}