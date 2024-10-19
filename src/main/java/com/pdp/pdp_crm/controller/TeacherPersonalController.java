package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/personal/balance")
public record TeacherPersonalController() {

    //TODO
    @GetMapping("/{id}")
    @Operation(summary = "Logikasi qilinishi kerak")
    public ResponseEntity<ResponseDTO<Void>> balance(@RequestParam Long teacherId,
                                                     @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

    //TODO
    @PostMapping("/filter")
    @Operation(summary = "Logikasi qilinishi kerak")
    public ResponseEntity<ResponseDTO<List<Void>>> balanceFilter(@RequestParam Long teacherId,
                                                                 @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(null);
    }

}