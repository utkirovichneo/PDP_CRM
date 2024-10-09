package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.teacher.TeacherDTO;
import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/auth/")
public record TeacherAuthController() {

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<TeacherDTO>> login(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseDTO.ok();
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<TeacherDTO>> me(){
        return ResponseDTO.ok();
    }
}