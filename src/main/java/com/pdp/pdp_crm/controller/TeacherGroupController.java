package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.attendance.AttendanceRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.TeacherService;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/")
public record TeacherGroupController(TeacherService service) {

                                            //!GROUP
    @PostMapping("/group/filter")
    public ResponseEntity<ResponseDTO<List<GroupDTO>>> filter(@RequestParam Long teacherId,
                                                              @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAll(teacherId, pageableRequest));
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<ResponseDTO<GroupDTO>> getGroup(@RequestParam Long teacherId,
                                                          @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.findById(teacherId, id));
    }

                                            //!Attendance - Yo'qlama
    @PutMapping("/attendance/lesson/confirm")
    public ResponseEntity<ResponseDTO<LessonAvailableDTO>> confirm(@RequestParam Long teacherId,
                                                                   @RequestParam LessonAvailableDTO dto){
        return ResponseDTO.ok(service.confirm(teacherId, dto));
    }

    @PostMapping("/attendance/lesson/create/lesson")
    public ResponseEntity<ResponseDTO<LessonAvailableDTO>> createLesson(@RequestParam Long teacherId,
                                                                        @RequestBody LessonAvailableRequestDTO dto){
        return ResponseDTO.ok(service.createLesson(teacherId, dto));
    }

    @PostMapping("/attendance/lesson/filter")
    public ResponseEntity<ResponseDTO<List<AttendanceDTO>>> filterAttendance(@RequestParam Long teacherId,
                                                                             @RequestParam Long groupId,
                                                                             @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.filterAttendance(teacherId, groupId, pageableRequest));
    }

    //TODO
    @PostMapping("/attendance/lesson/completed")
    public ResponseEntity<ResponseDTO<Boolean>> completedAttendance(@RequestParam Long teacherId,
                                                                    @RequestParam Long groupId,
                                                                    @RequestBody List<AttendanceRequestDTO> dto){
        return ResponseDTO.ok();
    }
}