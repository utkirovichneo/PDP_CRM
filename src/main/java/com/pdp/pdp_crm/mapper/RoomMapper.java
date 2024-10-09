package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.entity.Room;
import com.pdp.pdp_crm.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper extends EntityMapper<RoomDTO, Room> {
    @Override
    @Mapping(target = "center", ignore = true)
    Room toEntity(RoomDTO dto);

    @Override
    @Mapping(source = "center.id", target = "centerId")
    RoomDTO toDto(Room entity);

    @Override
    List<Room> toEntity(List<RoomDTO> list);

    @Override
    List<RoomDTO> toDto(List<Room> list);
}
