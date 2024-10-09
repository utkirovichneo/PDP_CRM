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
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public record CenterGroupController(GroupService groupService, CourseService courseService) {

                                    /*GROUP*/
    @GetMapping("/group/{id}")
    public ResponseEntity<ResponseDTO<GroupResDTO>> getGroup(@RequestParam Long centerId,
                                                             @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

    @PostMapping("/group")
    public ResponseEntity<ResponseDTO<GroupDTO>> saveGroup(@RequestParam Long centerId,
                                                           @RequestBody GroupRequestDTO dto){
        return ResponseDTO.ok();
    }

    @PostMapping("/group/filter")
    public ResponseEntity<ResponseDTO<List<GroupDTO>>> filterGroup(@RequestParam Long centerId,
                                                                   @Valid @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(null);
    }

    @PutMapping("/group/{id}")
    public ResponseEntity<ResponseDTO<GroupDTO>> updateGroup(@RequestParam Long centerId,
                                                             @PathVariable(name = "id") Long id,
                                                             @RequestBody GroupRequestDTO dto){
        return ResponseDTO.ok();
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteGroup(@RequestParam Long centerId,
                                                            @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }



                                    /*COURSE*/
    @PostMapping("/course")
    public ResponseEntity<ResponseDTO<CourseDTO>> saveCourse(@RequestParam Long centerId,
                                                             @RequestBody CourseRequestDTO dto){
        return ResponseDTO.ok();
    }

    @PostMapping("/course/filter")
    public ResponseEntity<ResponseDTO<List<CourseDTO>>> filterCourse(@RequestParam Long centerId,
                                                                     @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(null);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<ResponseDTO<CourseDTO>> getCourse(@RequestParam Long centerId,
                                                            @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<ResponseDTO<CourseDTO>> updateCourse(@RequestParam Long centerId,
                                                               @PathVariable(name = "id") Long id,
                                                               @RequestBody CourseRequestDTO dto){
        return ResponseDTO.ok();
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteCourse(@RequestParam Long centerId,
                                                             @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

}