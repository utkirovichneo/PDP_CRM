package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.attendance.AttendanceRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.entity.Attendance;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.GroupMapper;
import com.pdp.pdp_crm.repository.GroupRepository;
import com.pdp.pdp_crm.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final LessonAvailableServiceImpl lessonAvailableServiceImpl;
    private final AttendanceServiceImpl attendanceServiceImpl;
    private final GroupServiceImpl groupServiceImpl;
    private final StudentServiceImpl studentServiceImpl;
    private final AttendanceServiceImpl attendanceService;

    @Override
    public Page<GroupDTO> findAll(Long teacherId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("teacher.id", "=", teacherId));
        }
        else{
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("teacher.id", "=", teacherId)));
        }

        return groupRepository.findAll(
          new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(groupMapper::toDto);
    }

    @Override
    public GroupDTO findById(Long teacherId, Long groupId) {
        var group = groupRepository.findByIdAndTeacherId(groupId, teacherId)
                .orElseThrow(() -> new NotFoundException("Group"));
        return groupMapper.toDto(group);
    }

    @Override
    public LessonAvailableDTO confirm(Long teacherId, LessonAvailableDTO dto) {
        return lessonAvailableServiceImpl.confirm(teacherId, dto);
    }

    @Override
    public LessonAvailableDTO createLesson(Long teacherId, LessonAvailableRequestDTO dto) {
        return lessonAvailableServiceImpl.save(teacherId, dto);
    }

    @Override
    public Page<AttendanceDTO> filterAttendance(Long teacherId, Long groupId, PageableRequest pageableRequest) {
        return attendanceServiceImpl.filter(teacherId, groupId, pageableRequest);
    }

    @Override
    public Boolean completedAttendance(Long teacherId, Long groupId, List<AttendanceRequestDTO> dtos) {
        if(!CollectionUtils.isEmpty(dtos)){

            var group = groupServiceImpl.findByTeacherId(teacherId, groupId)
                    .orElseThrow(() -> new NotFoundException("Group"));

            for (AttendanceRequestDTO dto : dtos) {

                Attendance attendance = Attendance.builder()
                        .group(group)
                        .student(studentServiceImpl.findById(group.getCenter().getId(), dto.getStudentId()).orElseThrow(() -> new NotFoundException("Student")))
                        .date(dto.getDate())
                        .status(dto.getStatus())
                        .build();

                attendanceService.save(attendance);
            }

            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}