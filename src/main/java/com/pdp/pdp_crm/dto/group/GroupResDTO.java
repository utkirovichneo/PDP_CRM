package com.pdp.pdp_crm.dto.group;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.course.CourseDTO;
import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.dto.teacher.TeacherDTO;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.enums.GroupDays;
import com.pdp.pdp_crm.enums.GroupStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

// Bu DTO ko'p funksiyali. Groupni, Courseni, Shu oyga tegishli yo'qlamalar Listlarini berishi kerak
@Getter
@Setter
public class GroupResDTO {

    private Long id;

    private String groupName;

    private CenterDTO center;

    private CourseDTO course;

    private TeacherDTO teacher;

    private RoomDTO room;

    private List<GroupDays> days;

    private LocalTime startTime;

    private LocalTime endTime;

    private GroupStatus status;

    private EntityStatus entityStatus;

    private Long currentStage;

    private List<AttendanceDTO> attendance;

    private List<StudentDTO> students;
}
