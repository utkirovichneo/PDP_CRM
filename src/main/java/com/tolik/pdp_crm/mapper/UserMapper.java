package com.tolik.pdp_crm.mapper;

import org.mapstruct.Mapper;
import uz.pdp.initial_configuration_in_spring_boot_project.dto.user.UserResponseDTO;
import uz.pdp.initial_configuration_in_spring_boot_project.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserResponseDTO, User> {

    User toEntity(UserResponseDTO dto);

    UserResponseDTO toDto(User entity);

    List<User> toEntity(List<UserResponseDTO> list);

    List<UserResponseDTO> toDto(List<User> list);
}
