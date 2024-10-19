package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.attendance.AttendanceRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.TeacherService;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/")
public record TeacherGroupController(TeacherService service) {

                                            //!GROUP
    @PostMapping("/group/filter")
    @Operation(summary = "teacherning guruxlari")
    public ResponseEntity<ResponseDTO<List<GroupDTO>>> filter(@RequestParam Long teacherId,
                                                              @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAll(teacherId, pageableRequest));
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "get by id")
    public ResponseEntity<ResponseDTO<GroupDTO>> getGroup(@RequestParam Long teacherId,
                                                          @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.findById(teacherId, id));
    }

                                            //!Attendance - Yo'qlama
    @PostMapping("/attendance/lesson/allAvailable")
    @Operation(summary = "bu dars kunlarini listini olish sort/search")
    public ResponseEntity<ResponseDTO<List<LessonAvailableDTO>>> listAvailable(@RequestParam Long teacherId,
                                                                               @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAllLessonAvailable(teacherId, pageableRequest));
    }

    @PutMapping("/attendance/lesson/confirm/{id}")
    @Operation(summary = "dars bo'ladigan kunni start qilish")
    public ResponseEntity<ResponseDTO<LessonAvailableDTO>> confirm(@RequestParam Long teacherId,
                                                                   @RequestParam Long groupId,
                                                                   @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.confirm(teacherId, groupId, id));
    }

    //TODO
    @PostMapping("/attendance/lesson/create/lesson")
    @Operation(summary = "mobodo boshqa kuni dars o'tsa kiritish, buni chala logikasi bor")
    public ResponseEntity<ResponseDTO<LessonAvailableDTO>> createLesson(@RequestParam Long teacherId,
                                                                        @RequestBody LessonAvailableRequestDTO dto){
        return ResponseDTO.ok(service.createLesson(teacherId, dto));
    }

    @PostMapping("/attendance/lesson/filter/{lessonId}")
    @Operation(summary = "Bu kunlik yo'qlamalar ro'yxatini olish")
    public ResponseEntity<ResponseDTO<List<AttendanceDTO>>> filterAttendance(@RequestParam Long groupId,
                                                                             @PathVariable(name = "lessonId") Long lessonId,
                                                                             @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.filterAttendanceWithLessonId(groupId, lessonId, pageableRequest));
    }

    @PostMapping("/attendance/lesson/completed")
    @Operation(summary = "Yo'qlamani jo'natish")
    public ResponseEntity<ResponseDTO<Boolean>> completedAttendance(@RequestParam Long teacherId,
                                                                    @RequestParam Long groupId,
                                                                    @RequestBody List<AttendanceRequestDTO> dtos){
        return ResponseDTO.ok(service.completedAttendance(teacherId, groupId, dtos));
    }
}