package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/room")
public record RoomController(RoomService service) {
}