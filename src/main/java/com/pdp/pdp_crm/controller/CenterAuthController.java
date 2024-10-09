package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.service.AuthService;
import com.pdp.pdp_crm.util.AuthResponseDTO;
import com.pdp.pdp_crm.util.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public record CenterAuthController(AuthService service) {

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> register(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseDTO.ok(service.register(userRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseDTO.ok(service.login(userRequestDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseDTO<RefreshTokenResponseDTO>> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return ResponseDTO.ok(service.refreshToken(refreshTokenRequestDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> me(){
        return ResponseDTO.ok(service.me());
    }

    @PostMapping("/center")
    public ResponseEntity<ResponseDTO<CenterDTO>> centerCreate(@Valid @RequestBody CenterRequestDTO dto){
        return ResponseDTO.ok();
    }
}