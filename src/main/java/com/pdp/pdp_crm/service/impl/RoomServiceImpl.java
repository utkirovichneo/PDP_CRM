package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.RoomRepository;
import com.pdp.pdp_crm.service.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
}
