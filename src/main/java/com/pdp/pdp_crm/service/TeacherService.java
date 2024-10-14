package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

public interface TeacherService {

    Page<GroupDTO> findAll(Long teacherId, PageableRequest pageableRequest);

    GroupDTO findById(Long teacherId, Long groupId);

    LessonAvailableDTO confirm(Long teacherId, LessonAvailableDTO dto);

    LessonAvailableDTO createLesson(Long teacherId, LessonAvailableRequestDTO dto);

    Page<AttendanceDTO> filterAttendance(Long teacherId, Long groupId, PageableRequest pageableRequest);

}
