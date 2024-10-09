package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/personal/balance")
public record TeacherPersonalController() {

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> balance(@RequestParam Long teacherId,
                                                     @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseDTO<List<Void>>> balanceFilter(@RequestParam Long teacherId,
                                                                 @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(null);
    }

}