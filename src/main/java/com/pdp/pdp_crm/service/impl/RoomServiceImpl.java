package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.RoomRepository;
import com.pdp.pdp_crm.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

}
