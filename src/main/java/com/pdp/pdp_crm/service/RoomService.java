package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.room.RoomRequestDTO;

public interface RoomService {

    RoomDTO save(RoomRequestDTO roomRequestDTO, Long centerId);
}