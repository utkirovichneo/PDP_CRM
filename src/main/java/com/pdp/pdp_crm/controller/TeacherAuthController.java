package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.service.TeacherAuthService;
import com.pdp.pdp_crm.util.AuthResponseDTO;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/auth/")
public record TeacherAuthController(TeacherAuthService service) {

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseDTO.ok(service.login(userRequestDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> me(){
        return ResponseDTO.ok(service.me());
    }
}