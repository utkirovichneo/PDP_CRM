package com.tolik.pdp_crm.controller;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.token.RefreshTokenRequestDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.token.RefreshTokenResponseDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.user.UserRequestDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.user.UserResponseDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.service.AuthService;
import uz.pdp.initial_configuration_in_spring_boot_project.util.AuthResponseDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public record AuthUserController(AuthService authService) {

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> register(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseDTO.ok(authService.register(userRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseDTO.ok(authService.login(userRequestDTO));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<ResponseDTO<RefreshTokenResponseDTO>> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return ResponseDTO.ok(authService.refreshToken(refreshTokenRequestDTO));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAll(){
        return ResponseDTO.ok(authService.getAll());
    }
}