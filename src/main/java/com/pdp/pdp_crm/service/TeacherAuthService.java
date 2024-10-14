package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.util.AuthResponseDTO;

public interface TeacherAuthService {

    AuthResponseDTO login(UserRequestDTO userRequestDTO);

    UserResponseDTO me();

}