package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.entity.LessonAvailable;
import com.pdp.pdp_crm.mapper.LessonAvailableMapper;
import com.pdp.pdp_crm.repository.LessonAvailableRepository;
import com.pdp.pdp_crm.service.LessonAvailableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonAvailableServiceImpl implements LessonAvailableService {
    private final LessonAvailableRepository lessonAvailableRepository;
    private final LessonAvailableMapper lessonAvailableMapper;
    private final GroupServiceImpl groupServiceImpl;

    @Override
    public LessonAvailableDTO create(LessonAvailableRequestDTO dto) {
        return lessonAvailableMapper.toDto(
                lessonAvailableRepository.save(
                        LessonAvailable.builder()
                                .group(groupServiceImpl.findByIdOptional(dto.getGroupId()).orElseThrow(RuntimeException::new))
                                .date(dto.getDate())
                                .isLessonAvailable(dto.getIsLessonAvailable())
                                .build()
                )
        );
    }

    @Override
    public LessonAvailableDTO findById(Long id) {
        return lessonAvailableMapper.toDto(lessonAvailableRepository.findById(id).orElseThrow( RuntimeException::new));
    }

    @Override
    public LessonAvailableDTO update(Long id, LessonAvailableRequestDTO dto) {
        LessonAvailable lessonAvailable = lessonAvailableRepository.findById(id).orElseThrow(RuntimeException::new);
        lessonAvailable.setDate(dto.getDate());
        lessonAvailable.setIsLessonAvailable(dto.getIsLessonAvailable());
        return lessonAvailableMapper.toDto(lessonAvailableRepository.save(lessonAvailable));
    }
}
