package com.tolik.pdp_crm.service;

import uz.pdp.initial_configuration_in_spring_boot_project.dto.token.RefreshTokenRequestDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.token.RefreshTokenResponseDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.user.UserRequestDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.user.UserResponseDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.util.AuthResponseDTO;

import java.util.List;

public interface AuthService {

    AuthResponseDTO login(UserRequestDTO userRequestDTO);

    UserResponseDTO register(UserRequestDTO userRequestDTO);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    List<UserResponseDTO> getAll();

}