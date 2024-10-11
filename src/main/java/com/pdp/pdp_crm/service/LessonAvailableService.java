package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;

public interface LessonAvailableService {

    LessonAvailableDTO create(LessonAvailableRequestDTO dto);

    LessonAvailableDTO findById(Long id);

    LessonAvailableDTO update(Long id, LessonAvailableRequestDTO dto);

}