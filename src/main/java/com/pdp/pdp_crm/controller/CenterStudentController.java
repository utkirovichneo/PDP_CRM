package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.collection.CollectionDTO;
import com.pdp.pdp_crm.dto.payment.PaymentDTO;
import com.pdp.pdp_crm.dto.payment.PaymentRequestDTO;
import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.StudentService;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/student")
public record CenterStudentController(StudentService service) {

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<StudentDTO>> get(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok();
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<StudentDTO>> save(@RequestParam Long centerId,
                                                        @RequestBody StudentDTO studentDTO) {
        return ResponseDTO.ok();
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseDTO<List<StudentDTO>>> filter(@RequestParam Long centerId,
                                                                @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<StudentDTO>> update(@RequestParam Long centerId,
                                                          @PathVariable(name = "id") Long id,
                                                          @RequestBody StudentDTO dto) {
        return ResponseDTO.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

    @PostMapping("/payment")
    public ResponseEntity<ResponseDTO<PaymentDTO>> payment(@RequestParam Long centerId,
                                                           @RequestBody PaymentRequestDTO dto){
        return ResponseDTO.ok();
    }

    @PostMapping("/payment/filter")
    public ResponseEntity<ResponseDTO<List<PaymentDTO>>> paymentFilter(@RequestParam Long centerId,
                                                                       @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(null);
    }

    @GetMapping("/collection")
    public ResponseEntity<ResponseDTO<CollectionDTO>> getCollection(@RequestParam Long centerId,
                                                                    @RequestParam Long studentId){
        return ResponseDTO.ok();
    }
}