package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.room.RoomRequestDTO;
import com.pdp.pdp_crm.entity.Room;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RoomService {

    RoomDTO save(Long centerId, RoomRequestDTO dto);

    RoomDTO getRoom(Long centerId, Long id);

    Page<RoomDTO> findAll(Long centerId, PageableRequest pageable);

    RoomDTO update(Long centerId, Long id, RoomRequestDTO dto);

    Boolean delete(Long centerId, Long id);

    Optional<Room> findById(Long centerId, Long id);

}