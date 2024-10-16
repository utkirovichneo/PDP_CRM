package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.service.CenterAuthService;
import com.pdp.pdp_crm.service.TeacherAuthService;
import com.pdp.pdp_crm.util.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherAuthServiceImpl implements TeacherAuthService {

    private final CenterAuthService centerAuthService;


    @Override
    public AuthResponseDTO login(UserRequestDTO userRequestDTO) {
        return centerAuthService.login(userRequestDTO);
    }

    @Override
    public UserResponseDTO me() {
        return centerAuthService.me();
    }
}