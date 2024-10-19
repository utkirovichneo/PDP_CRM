package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.entity.LessonAvailable;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.LessonAvailableMapper;
import com.pdp.pdp_crm.repository.LessonAvailableRepository;
import com.pdp.pdp_crm.service.AttendanceService;
import com.pdp.pdp_crm.service.LessonAvailableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonAvailableServiceImpl implements LessonAvailableService {

    private final LessonAvailableRepository lessonAvailableRepository;
    private final LessonAvailableMapper lessonAvailableMapper;
    @Autowired
    @Lazy
    public void setLessonAvailableServiceImpl(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    private AttendanceService attendanceService;

    @Lazy
    @Autowired
    public void setGroupServiceImpl(GroupServiceImpl groupServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
    }
    private GroupServiceImpl groupServiceImpl;

    @Override
    public LessonAvailableDTO save(Long teacherId, LessonAvailableRequestDTO dto) {
        return lessonAvailableMapper.toDto(lessonAvailableRepository.save(LessonAvailable.builder()
                .group(groupServiceImpl.findByTeacherId(teacherId, dto.getGroupId()).orElseThrow(() -> new NotFoundException("Group")))
                .isLessonAvailable(dto.getIsLessonAvailable())
                .date(dto.getDate())
                .build()));
    }

    @Override
    public LessonAvailableDTO confirm(Long teacherId, Long groupId, Long id) {

        var lesson = lessonAvailableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LessonAvailable"));

        lesson.setIsLessonAvailable(Boolean.TRUE);
        lesson.setDate(LocalDate.now());

        attendanceService.createAttendance(teacherId, groupId);

        return lessonAvailableMapper.toDto(lessonAvailableRepository.save(lesson));
    }

    @Override
    public Page<LessonAvailableDTO> findAll(Long teacherId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("group.teacher.id", "=", teacherId));
        }
        else{
            pageableRequest.setSearch(List.of(new SearchCriteria("group.teacher.id", "=", teacherId)));
        }
        return lessonAvailableRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(lessonAvailableMapper::toDto);
    }

}