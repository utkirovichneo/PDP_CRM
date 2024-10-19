package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.service.TeacherAuthService;
import com.pdp.pdp_crm.util.AuthResponseDTO;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/auth/")
public record TeacherAuthController(TeacherAuthService service) {

    @PostMapping("/login")
    @Operation(summary = "O'qituvchi login qilishi, raqam va passwordni admin tarafda membercreateda qilinadi")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseDTO.ok(service.login(userRequestDTO));
    }

    @GetMapping("/me")
    @Operation(summary = "tizimdagi odam haqida malumot JWT token orqali")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> me(){
        return ResponseDTO.ok(service.me());
    }
}