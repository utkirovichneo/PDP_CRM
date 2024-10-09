package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.LessonAvailableRepository;
import com.pdp.pdp_crm.service.LessonAvailableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonAvailableServiceImpl implements LessonAvailableService {
    private final LessonAvailableRepository lessonAvailableRepository;

}
