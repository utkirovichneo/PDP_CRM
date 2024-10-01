package com.tolik.pdp_crm.controller;

import com.tolik.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.tolik.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.tolik.pdp_crm.dto.user.UserRequestDTO;
import com.tolik.pdp_crm.dto.user.UserResponseDTO;
import com.tolik.pdp_crm.service.AuthService;
import com.tolik.pdp_crm.util.AuthResponseDTO;
import com.tolik.pdp_crm.util.ResponseDTO;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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