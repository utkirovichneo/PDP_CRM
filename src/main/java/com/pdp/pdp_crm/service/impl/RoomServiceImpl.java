package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.room.RoomRequestDTO;
import com.pdp.pdp_crm.entity.Room;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.RoomMapper;
import com.pdp.pdp_crm.repository.RoomRepository;
import com.pdp.pdp_crm.service.CenterService;
import com.pdp.pdp_crm.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final CenterService centerService;

    @Override
    public RoomDTO save(Long centerId, RoomRequestDTO dto) {

        var center = centerService.findById(centerId)
                .orElseThrow(() -> new NotFoundException("Center"));

        var room = roomRepository.save(
                Room.builder()
                        .center(center)
                        .name(dto.getName())
                        .number(dto.getNumber())
                        .capacity(dto.getCapacity())
                        .entityStatus(EntityStatus.ACTIVE)
                        .definition(dto.getDefinition())
                        .build()
        );

        return roomMapper.toDto(room);
    }

    @Override
    public RoomDTO getRoom(Long centerId, Long id) {
        return roomMapper.toDto(
                        roomRepository.findByIdAndCenterId(id, centerId)
                                .orElseThrow(() -> new NotFoundException("Room")));
    }

    @Override
    public Page<RoomDTO> findAll(Long centerId, PageableRequest pageable) {
        if(CollectionUtils.isEmpty(pageable.getSearch())){
            pageable.getSearch().add(new SearchCriteria("center.id", "=", centerId));
        }
        else{
            pageable.setSearch(new ArrayList<>(Arrays.asList(new SearchCriteria("center.id", "=", centerId))));
        }
        return roomRepository.findAll(
                new SearchSpecification<>(pageable.getSearch()),
                PageableRequestUtil.toPageable(pageable)
        ).map(roomMapper::toDto);
    }

    @Override
    public RoomDTO update(Long centerId, Long id, RoomRequestDTO dto) {

        var room = roomRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Room"));

        room.setName(dto.getName());
        room.setNumber(dto.getNumber());
        room.setCapacity(dto.getCapacity());
        room.setDefinition(dto.getDefinition());

        return roomMapper.toDto(roomRepository.save(room));
    }

    @Override
    public Boolean delete(Long centerId, Long id) {
        var room = roomRepository.findByIdAndCenterId(id, centerId).orElseThrow(() -> new NotFoundException("Room"));

        room.setEntityStatus(EntityStatus.ARCHIVED);

        roomRepository.save(room);

        return Boolean.TRUE;
    }

    @Override
    public Optional<Room> findById(Long centerId, Long id) {
        return roomRepository.findByIdAndCenterId(id, centerId);
    }
}