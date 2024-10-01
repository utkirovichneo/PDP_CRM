package com.tolik.pdp_crm.mapper;

import com.tolik.pdp_crm.dto.user.UserResponseDTO;
import com.tolik.pdp_crm.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserResponseDTO, User> {

    User toEntity(UserResponseDTO dto);

    UserResponseDTO toDto(User entity);

    List<User> toEntity(List<UserResponseDTO> list);

    List<UserResponseDTO> toDto(List<User> list);
}
