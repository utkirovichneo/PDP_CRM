package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.member.MemberDTO;
import com.pdp.pdp_crm.dto.member.MemberRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.MemberService;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/member")
public record CenterMemberController(MemberService service) {

    @GetMapping("/{id}")
    @Operation(summary = "get by id")
    public ResponseEntity<ResponseDTO<MemberDTO>> get(@RequestParam Long centerId,
                                                      @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok(service.getById(centerId, id));
    }

    @PostMapping("create member, yani ishchi yoki teacher yaratish")
    public ResponseEntity<ResponseDTO<MemberDTO>> save(@RequestParam Long centerId,
                                                       @RequestBody MemberRequestDTO dto) {
        return ResponseDTO.ok(service.save(centerId, dto));
    }

    @PostMapping("/filter")
    @Operation(summary = "findAll search/sort qilish")
    public ResponseEntity<ResponseDTO<List<MemberDTO>>> filter(@RequestParam Long centerId,
                                                               @Valid @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.getAll(centerId, pageableRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update qilish")
    public ResponseEntity<ResponseDTO<MemberDTO>> update(@RequestParam Long centerId,
                                                         @PathVariable(name = "id") Long id,
                                                         @RequestBody MemberRequestDTO dto) {
        return ResponseDTO.ok(service.update(centerId, id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete qilish bu ARCHIVE qilib beradi")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok(service.delete(centerId, id));
    }

}