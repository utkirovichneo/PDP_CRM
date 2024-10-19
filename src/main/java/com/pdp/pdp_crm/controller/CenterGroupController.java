package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.course.CourseDTO;
import com.pdp.pdp_crm.dto.course.CourseRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.group.GroupRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupResDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.CourseService;
import com.pdp.pdp_crm.service.GroupService;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public record CenterGroupController(GroupService group, CourseService course) {

                                    //!GROUP
    @GetMapping("/group/{id}")
    @Operation(summary = "get by id")
    public ResponseEntity<ResponseDTO<GroupResDTO>> getGroup(@RequestParam Long centerId,
                                                             @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(group.findGroupResDTO(centerId, id));
    }

    @PostMapping("/group")
    @Operation(summary = "create group")
    public ResponseEntity<ResponseDTO<GroupDTO>> saveGroup(@RequestParam Long centerId,
                                                           @RequestBody GroupRequestDTO dto){
        return ResponseDTO.ok(group.save(centerId, dto));
    }

    @PostMapping("/group/filter")
    @Operation(summary = "get all group or search/sort")
    public ResponseEntity<ResponseDTO<List<GroupDTO>>> filterGroup(@RequestParam Long centerId,
                                                                   @Valid @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(group.findAll(centerId, pageableRequest));
    }

    @PutMapping("/group/{id}")
    @Operation(summary = "update qilish")
    public ResponseEntity<ResponseDTO<GroupDTO>> updateGroup(@RequestParam Long centerId,
                                                             @PathVariable(name = "id") Long id,
                                                             @RequestBody GroupRequestDTO dto){
        return ResponseDTO.ok(group.update(centerId, id, dto));
    }

    @DeleteMapping("/group/{id}")
    @Operation(summary = "delete qilish bu ARCHIVE qilib beradi")
    public ResponseEntity<ResponseDTO<Boolean>> deleteGroup(@RequestParam Long centerId,
                                                            @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok(group.delete(centerId, id));
    }

    @PutMapping("/group/start/{id}")
    @Operation(summary = "Bu guruxga dars boshlanganini bildiradi. Yani 1-modul 1-dars uchun start")
    public ResponseEntity<ResponseDTO<Boolean>> startGroup(@RequestParam Long centerId,
                                                           @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok(group.start(centerId, id));
    }


                                    //!COURSE
    @PostMapping("/course")
    @Operation(summary = "Course yaratish")
    public ResponseEntity<ResponseDTO<CourseDTO>> saveCourse(@RequestParam Long centerId,
                                                             @RequestBody CourseRequestDTO dto){
        return ResponseDTO.ok(course.save(centerId, dto));
    }

    @PostMapping("/course/filter")
    @Operation(summary = "Kurslar ro'yxatini search/sort qilish")
    public ResponseEntity<ResponseDTO<List<CourseDTO>>> filterCourse(@RequestParam Long centerId,
                                                                     @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(course.findAll(centerId, pageableRequest));
    }

    @GetMapping("/course/{id}")
    @Operation(summary = "get by id")
    public ResponseEntity<ResponseDTO<CourseDTO>> getCourse(@RequestParam Long centerId,
                                                            @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(course.findOne(centerId, id));
    }

    @PutMapping("/course/{id}")
    @Operation(summary = "update qilish")
    public ResponseEntity<ResponseDTO<CourseDTO>> updateCourse(@RequestParam Long centerId,
                                                               @PathVariable(name = "id") Long id,
                                                               @RequestBody CourseRequestDTO dto){
        return ResponseDTO.ok(course.update(centerId, id, dto));
    }

    @DeleteMapping("/course/{id}")
    @Operation(summary = "delete qilish bu ARCHIVE qilib beradi")
    public ResponseEntity<ResponseDTO<Boolean>> deleteCourse(@RequestParam Long centerId,
                                                             @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(course.delete(centerId, id));
    }

}