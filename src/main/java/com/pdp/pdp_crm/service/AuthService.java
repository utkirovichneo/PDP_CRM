package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.util.AuthResponseDTO;

import java.util.List;

public interface AuthService {

    AuthResponseDTO login(UserRequestDTO userRequestDTO);

    UserResponseDTO register(UserRequestDTO userRequestDTO);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    UserResponseDTO me();

}