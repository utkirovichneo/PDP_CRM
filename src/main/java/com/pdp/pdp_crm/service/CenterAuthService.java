package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.entity.User;
import com.pdp.pdp_crm.util.AuthResponseDTO;

import java.util.Optional;


public interface CenterAuthService {

    AuthResponseDTO login(UserRequestDTO userRequestDTO);

    UserResponseDTO register(UserRequestDTO userRequestDTO);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    UserResponseDTO me();

    CenterDTO createCenter(CenterRequestDTO centerRequestDTO);

    Optional<User> findById(Long id);

}