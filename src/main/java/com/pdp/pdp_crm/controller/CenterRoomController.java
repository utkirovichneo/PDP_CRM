package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.RoomService;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/room")
public record CenterRoomController(RoomService service) {

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<RoomDTO>> get(@RequestParam Long centerId,
                                                    @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok();
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<RoomDTO>> save(@RequestParam Long centerId,
                                                     @RequestBody RoomDTO roomDTO) {
        return ResponseDTO.ok();
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseDTO<List<RoomDTO>>> filter(@RequestParam Long centerId,
                                                             @RequestBody PageableRequest pageableRequest) {
        return ResponseDTO.page(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<RoomDTO>> update(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id,
                                                       @RequestBody RoomDTO roomDTO) {
        return ResponseDTO.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok();
    }
}