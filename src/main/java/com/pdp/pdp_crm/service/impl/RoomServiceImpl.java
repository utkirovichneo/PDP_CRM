package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.room.RoomRequestDTO;
import com.pdp.pdp_crm.entity.Room;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.mapper.RoomMapper;
import com.pdp.pdp_crm.repository.CenterRepository;
import com.pdp.pdp_crm.repository.RoomRepository;
import com.pdp.pdp_crm.repository.SalaryRepository;
import com.pdp.pdp_crm.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final CenterServiceImpl centerServiceImpl;
    private final CenterRepository centerRepository;


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

    @Override
    public RoomDTO create(RoomRequestDTO dto) {
        return roomMapper.toDto(
                roomRepository.save(
                        Room.builder()
//                                .center()
                                .name(dto.getName())
                                .number(dto.getNumber())
                                .capacity(dto.getCapacity())
                                .definition(dto.getDefinition())
                                .entityStatus(EntityStatus.ACTIVE)
                                .build()

                )
        );
    }

    @Override
    public RoomDTO findById(Long id) {
        return roomMapper.toDto(roomRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<Room> findByIdOptional(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public RoomDTO update(Long id, RoomRequestDTO dto) {
        Room room = roomRepository.findById(id).orElseThrow(RuntimeException::new);
        room.setName(dto.getName());
        room.setNumber(dto.getNumber());
        room.setCapacity(dto.getCapacity());
        room.setDefinition(dto.getDefinition());
        return roomMapper.toDto(roomRepository.save(room));
    }
}
