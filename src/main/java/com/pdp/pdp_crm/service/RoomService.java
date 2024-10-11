package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.room.RoomRequestDTO;
import com.pdp.pdp_crm.entity.Room;

import java.util.Optional;


public interface RoomService {

    RoomDTO save(RoomRequestDTO roomRequestDTO, Long centerId);

    RoomDTO create(RoomRequestDTO dto);

    RoomDTO findById(Long id);
    Optional<Room> findByIdOptional(Long id);

    RoomDTO update(Long id, RoomRequestDTO dto);

}