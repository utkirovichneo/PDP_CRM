package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.attendance.AttendanceRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/")
public record TeacherGroupController() {

                                            /*GROUP*/
    @PostMapping("/group/filter")
    public ResponseEntity<ResponseDTO<GroupDTO>> filter(@RequestParam Long teacherId){
        return ResponseDTO.ok();
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<ResponseDTO<GroupDTO>> getGroup(@RequestParam Long teacherId,
                                                          @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

                                            /*Attendance - Yo'qlama*/
    @PostMapping("/attendance/lesson/confirm")
    public ResponseEntity<ResponseDTO<LessonAvailableDTO>> confirm(@RequestParam Long teacherId,
                                                                   @RequestParam LessonAvailableRequestDTO dto){
        return ResponseDTO.ok();
    }

    @PostMapping("/attendance/lesson/create/lesson")
    public ResponseEntity<ResponseDTO<LessonAvailableDTO>> createLesson(@RequestParam Long teacherId,
                                                                        @RequestBody LessonAvailableRequestDTO dto){
        return ResponseDTO.ok();
    }




    @PostMapping("/attendance/lesson/filter")
    public ResponseEntity<ResponseDTO<List<AttendanceDTO>>> filterAttendance(@RequestParam Long teacherId,
                                                                             @RequestParam Long groupId){
        return ResponseDTO.page(null);
    }

    @PostMapping("/attendance/lesson/completed")
    public ResponseEntity<ResponseDTO<Boolean>> completedAttendance(@RequestParam Long teacherId,
                                                                    @RequestParam Long groupId,
                                                                    @RequestBody List<AttendanceRequestDTO> dto){
        return ResponseDTO.ok();
    }
}