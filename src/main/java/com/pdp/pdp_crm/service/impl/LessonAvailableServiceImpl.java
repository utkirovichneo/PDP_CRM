package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.entity.LessonAvailable;
import com.pdp.pdp_crm.mapper.LessonAvailableMapper;
import com.pdp.pdp_crm.repository.LessonAvailableRepository;
import com.pdp.pdp_crm.service.LessonAvailableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonAvailableServiceImpl implements LessonAvailableService {

    private final LessonAvailableRepository lessonAvailableRepository;
    private final LessonAvailableMapper lessonAvailableMapper;

    @Lazy
    @Autowired
    public void setGroupServiceImpl(GroupServiceImpl groupServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
    }
    private GroupServiceImpl groupServiceImpl;

    @Override
    public LessonAvailableDTO save(Long teacherId, LessonAvailableRequestDTO dto) {
        return lessonAvailableMapper.toDto(lessonAvailableRepository.save(LessonAvailable.builder()
                .group(groupServiceImpl.findByTeacherId(teacherId, dto.getGroupId()).orElseThrow(() -> new RuntimeException("Grouo not found")))
                .isLessonAvailable(dto.getIsLessonAvailable())
                .date(dto.getDate())
                .build()));
    }

    @Override
    public LessonAvailableDTO confirm(Long teacherId, LessonAvailableDTO dto) {

        var lesson = lessonAvailableRepository.findByIdAndGroupId(dto.getId(), dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("LessonAvailable not found"));

        lesson.setIsLessonAvailable(dto.getIsLessonAvailable());
        lesson.setDate(dto.getDate());

        return lessonAvailableMapper.toDto(lessonAvailableRepository.save(lesson));
    }
}