package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.entity.Attendance;
import com.pdp.pdp_crm.entity.LessonAvailable;
import com.pdp.pdp_crm.enums.AttendanceStatus;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.AttendanceMapper;
import com.pdp.pdp_crm.repository.AttendanceRepository;
import com.pdp.pdp_crm.repository.LessonAvailableRepository;
import com.pdp.pdp_crm.service.AttendanceService;
import com.pdp.pdp_crm.service.GroupService;
import com.pdp.pdp_crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final GroupService groupService;
    private final StudentService studentService;
    private final LessonAvailableRepository lessonAvailableRepository;

    @Override
    public void createAttendance(Long teacherId, Long groupId) {

        var group = groupService.findByTeacherId(teacherId, groupId).orElseThrow(() -> new NotFoundException("Group"));
        Long centerId = group.getCenter().getId();
        List<StudentDTO> allStudents = studentService.findAllStudents(centerId, groupId);

        allStudents.forEach(student ->
                        attendanceRepository.save(Attendance.builder()
                        .group(group)
                        .student(studentService.findById(centerId, student.getId()).orElseThrow(() -> new NotFoundException("Student")))
                        .date(LocalDate.now())
                        .status(AttendanceStatus.NEW)
                        .build())
                );
    }

    @Override
    public Page<AttendanceDTO> filterLessonAttandance(Long groupId, Long lessonId, PageableRequest pageableRequest) {
        var lesson = lessonAvailableRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson"));

        if(lesson.getIsLessonAvailable()) {
            if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
                pageableRequest.getSearch().add(new SearchCriteria("date", "=", lesson.getDate()));
                pageableRequest.getSearch().add(new SearchCriteria("group.id", "=", groupId));
            }
            else{
                pageableRequest.setSearch(List.of(
                        new SearchCriteria("date", "=", lesson.getDate()),
                        new SearchCriteria("group.id", "=", groupId)
                ));
            }
            return attendanceRepository.findAll(
              new SearchSpecification<>(pageableRequest.getSearch()),
              PageableRequestUtil.toPageable(pageableRequest)
            ).map(attendanceMapper::toDto);
        }
        return null;
    }

    @Override
    public AttendanceDTO save(Attendance attendance){
        return attendanceMapper.toDto(attendanceRepository.save(attendance));
    }

    @Override
    public Page<AttendanceDTO> filter(Long teacherId, Long groupId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("group.id", "=", groupId));
            pageableRequest.getSearch().add(new SearchCriteria("group.teacher.id", "=", teacherId));
        }
        else{
            pageableRequest.setSearch(List.of(new SearchCriteria("group.id", "=", groupId),
                                              new SearchCriteria("group.teacher.id", "=", teacherId)));
        }
        return attendanceRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(attendanceMapper::toDto);
    }
}