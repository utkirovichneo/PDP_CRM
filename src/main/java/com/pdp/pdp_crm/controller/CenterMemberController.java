package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.member.MemberDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.MemberService;
import com.pdp.pdp_crm.util.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/member")
public record CenterMemberController(MemberService service) {

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<MemberDTO>> get(@RequestParam Long centerId,
                                                      @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok();
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<MemberDTO>> save(@RequestParam Long centerId,
                                                       @RequestBody MemberDTO dto) {
        return ResponseDTO.ok();
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseDTO<List<MemberDTO>>> filter(@RequestParam Long centerId,
                                                               @Valid @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<MemberDTO>> update(@RequestParam Long centerId,
                                                         @PathVariable(name = "id") Long id,
                                                         @RequestBody MemberDTO dto) {
        return ResponseDTO.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok();
    }

}