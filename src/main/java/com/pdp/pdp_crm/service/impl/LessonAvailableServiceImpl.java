package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.LessonAvailableRepository;
import com.pdp.pdp_crm.service.LessonAvailableService;
import org.springframework.stereotype.Service;

@Service
public class LessonAvailableServiceImpl implements LessonAvailableService {
    private final LessonAvailableRepository lessonAvailableRepository;

    public LessonAvailableServiceImpl(LessonAvailableRepository lessonAvailableRepository) {
        this.lessonAvailableRepository = lessonAvailableRepository;
    }
}
