package com.tolik.pdp_crm.service;


import com.tolik.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.tolik.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.tolik.pdp_crm.dto.user.UserRequestDTO;
import com.tolik.pdp_crm.dto.user.UserResponseDTO;
import com.tolik.pdp_crm.util.AuthResponseDTO;

import java.util.List;

public interface AuthService {

    AuthResponseDTO login(UserRequestDTO userRequestDTO);

    UserResponseDTO register(UserRequestDTO userRequestDTO);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    List<UserResponseDTO> getAll();

}