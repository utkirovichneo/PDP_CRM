package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.collection.CollectionDTO;
import com.pdp.pdp_crm.dto.payment.PaymentDTO;
import com.pdp.pdp_crm.dto.payment.PaymentRequestDTO;
import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.dto.student.StudentRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.StudentService;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/student")
public record CenterStudentController(StudentService service) {

    @GetMapping("/{id}")
    @Operation(summary = "get by id")
    public ResponseEntity<ResponseDTO<StudentDTO>> get(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id) {
        return ResponseDTO.ok(service.getStudentById(centerId, id));
    }

    @PostMapping("")
    @Operation(summary = "student yaratish")
    public ResponseEntity<ResponseDTO<StudentDTO>> save(@RequestParam Long centerId,
                                                        @RequestBody StudentRequestDTO studentDTO) {
        return ResponseDTO.ok(service.save(centerId, studentDTO));
    }

    @PostMapping("/filter")
    @Operation(summary = "studentlar ro'yxati sort/search")
    public ResponseEntity<ResponseDTO<List<StudentDTO>>> filter(@RequestParam Long centerId,
                                                                @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAll(centerId, pageableRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update qilish")
    public ResponseEntity<ResponseDTO<StudentDTO>> update(@RequestParam Long centerId,
                                                          @PathVariable(name = "id") Long id,
                                                          @RequestBody StudentRequestDTO dto) {
        return ResponseDTO.ok(service.update(centerId, id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete qilish bu ARCHIVE qilib beradi")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@RequestParam Long centerId,
                                                       @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.delete(centerId, id));
    }

    @PostMapping("/payment")
    @Operation(summary = "to'lov qilish")
    public ResponseEntity<ResponseDTO<PaymentDTO>> payment(@RequestParam Long centerId,
                                                           @RequestBody PaymentRequestDTO dto){
        return ResponseDTO.ok(service.payment(centerId, dto));
    }

    @PostMapping("/payment/filter")
    @Operation(summary = "Talabalar qilgan to'lovlarni ro'yxatini olish sort/search")
    public ResponseEntity<ResponseDTO<List<PaymentDTO>>> paymentFilter(@RequestParam Long centerId,
                                                                       @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAllPayments(centerId, pageableRequest));
    }

    @GetMapping("/collection")
    @Operation(summary = "Bu to'lov ma'lumotlarini olish")
    public ResponseEntity<ResponseDTO<CollectionDTO>> getCollection(@RequestParam Long centerId,
                                                                    @RequestParam Long studentId){
        return ResponseDTO.ok(service.getCollection(centerId, studentId));
    }
}