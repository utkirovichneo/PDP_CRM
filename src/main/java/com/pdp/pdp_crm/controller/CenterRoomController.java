package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.room.RoomDTO;
import com.pdp.pdp_crm.dto.room.RoomRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.RoomService;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/room")
public record CenterRoomController(RoomService service) {

    @GetMapping("/{id}")
    @Operation(summary = "get by id")
    public ResponseEntity<ResponseDTO<RoomDTO>> get(@RequestParam Long centerId,
                                                    @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok(service.getRoom(centerId, id));
    }

    @PostMapping("")
    @Operation(summary = "Xona yaratish")
    public ResponseEntity<ResponseDTO<RoomDTO>> save(@RequestParam Long centerId,
                                                     @RequestBody RoomRequestDTO roomRequestDTO) {
        return ResponseDTO.ok(service.save(centerId, roomRequestDTO));
    }

    @PostMapping("/filter")
    @Operation(summary = "find all qilish sort/search")
    public ResponseEntity<ResponseDTO<List<RoomDTO>>> filter(@RequestParam Long centerId,
                                                             @RequestBody PageableRequest pageableRequest) {
        return ResponseDTO.page(service.findAll(centerId, pageableRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "xonani update qilish")
    public ResponseEntity<ResponseDTO<RoomDTO>> update(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id,
                                                       @RequestBody RoomRequestDTO roomRequestDTO) {
        return ResponseDTO.ok(service.update(centerId, id, roomRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete qilish bu ARCHIVE qilib beradi")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok(service.delete(centerId, id));
    }
}