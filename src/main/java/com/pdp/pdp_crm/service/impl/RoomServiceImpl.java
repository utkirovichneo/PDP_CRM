package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.room.RoomRequestDTO;
import com.pdp.pdp_crm.entity.Room;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.mapper.RoomMapper;
import com.pdp.pdp_crm.repository.RoomRepository;
import com.pdp.pdp_crm.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final CenterServiceImpl centerServiceImpl;

    @Override
    public RoomDTO save(RoomRequestDTO roomRequestDTO, Long centerId) {
        return roomMapper.toDto(roomRepository.save(
                Room.builder()
                        .name(roomRequestDTO.getName())
                        .number(roomRequestDTO.getNumber())
                        .capacity(roomRequestDTO.getCapacity())
                        .definition(roomRequestDTO.getDefinition())
                        .entityStatus(EntityStatus.ACTIVE)
                        .center(centerServiceImpl.findById(centerId)).build()
        ));
    }
}
