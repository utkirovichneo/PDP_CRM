package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.finance.FinanceDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.service.FinanceService;
import com.pdp.pdp_crm.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/finance")
public record CenterFinanceController(FinanceService service) {

    @GetMapping("")
    @Operation(summary = "Bu balanseda qancha pul borligi")
    public ResponseEntity<ResponseDTO<FinanceDTO>> getFinance(@RequestParam Long centerId) {
        return ResponseDTO.ok(service.getOne(centerId));
    }

    @PostMapping("/income/filter")
    @Operation(summary = "Barcha kirimlar")
    public ResponseEntity<ResponseDTO<List<FinanceIncomeDTO>>> filterIncome(@RequestParam Long centerId,
                                                                            @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAllIncomeFinance(centerId, pageableRequest));
    }

    @GetMapping("/income/{id}")
    @Operation(summary = "Get by id")
    public ResponseEntity<ResponseDTO<FinanceIncomeDTO>> getFinanceIncome(@RequestParam Long centerId,
                                                                          @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.findOneIncomeFinance(centerId, id));
    }

    @PostMapping("/outcome/filter")
    @Operation(summary = "Barcha chiqimlar")
    public ResponseEntity<ResponseDTO<List<FinanceOutcomeDTO>>> filterOutcome(@RequestParam Long centerId,
                                                                              @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAllOutcomeFinance(centerId, pageableRequest));
    }

    @GetMapping("/outcome/{id}")
    @Operation(summary = "get by id")
    public ResponseEntity<ResponseDTO<FinanceOutcomeDTO>> getFinanceOutcome(@RequestParam Long centerId,
                                                                            @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.findOneOutcomeFinance(centerId, id));
    }

    @PostMapping("/expense")
    @Operation(summary = "Bu qandaydir chiqim qilish kerak bo'lsa")
    public ResponseEntity<ResponseDTO<FinanceOutcomeDTO>> expense(@RequestParam Long centerId,
                                                                  @RequestBody FinanceOutcomeRequestDTO dto){
        return ResponseDTO.ok(service.ownerExpense(centerId, dto));
    }
}