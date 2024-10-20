package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.service.CenterAuthService;
import com.pdp.pdp_crm.util.AuthResponseDTO;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
public record CenterAuthController(CenterAuthService service) {

    @PostMapping("/register")
    @Operation(summary = "Register")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> register(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseDTO.ok(service.register(userRequestDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseDTO.ok(service.login(userRequestDTO));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token")
    public ResponseEntity<ResponseDTO<RefreshTokenResponseDTO>> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return ResponseDTO.ok(service.refreshToken(refreshTokenRequestDTO));
    }

    @GetMapping("/me")
    @Operation(summary = "me")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> me() {
        return ResponseDTO.ok(service.me());
    }

    @PostMapping("/center")
    @Operation(summary = "Create center. Register qilgan odam center yaratishi shart")
    public ResponseEntity<ResponseDTO<CenterDTO>> centerCreate(@Valid @RequestBody CenterRequestDTO dto) {
        return ResponseDTO.ok(service.createCenter(dto));
    }

    @PostMapping(value = "/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "logo")
    public ResponseEntity<ResponseDTO<CenterDTO>> logo(@RequestPart("logo") MultipartFile logo) {
        return ResponseDTO.ok(service.saveLogo(logo));
    }
}