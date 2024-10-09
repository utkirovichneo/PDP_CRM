package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.finance.FinanceDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeRequestDTO;
import com.pdp.pdp_crm.service.FinanceService;
import com.pdp.pdp_crm.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/finance")
public record CenterFinanceController(FinanceService service) {

    @GetMapping("")
    public ResponseEntity<ResponseDTO<FinanceDTO>> getFinance(@RequestParam Long centerId) {
        return ResponseDTO.ok();
    }

    @PostMapping("/income/filter")
    public ResponseEntity<ResponseDTO<List<FinanceIncomeDTO>>> filterIncome(@RequestParam Long centerId){
        return ResponseDTO.page(null);
    }

    @GetMapping("/income/{id}")
    public ResponseEntity<ResponseDTO<FinanceIncomeDTO>> getFinanceIncome(@RequestParam Long centerId,
                                                                          @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

    @PostMapping("/outcome/filter")
    public ResponseEntity<ResponseDTO<List<FinanceOutcomeDTO>>> filterOutcome(@RequestParam Long centerId){
        return ResponseDTO.page(null);
    }

    @GetMapping("/outcome/{id}")
    public ResponseEntity<ResponseDTO<FinanceOutcomeDTO>> getFinanceOutcome(@RequestParam Long centerId,
                                                                            @PathVariable(name = "id") Long id){
        return ResponseDTO.ok();
    }

    @PostMapping("/expense")
    public ResponseEntity<ResponseDTO<FinanceOutcomeDTO>> expense(@RequestParam Long centerId,
                                                                  @RequestBody FinanceOutcomeRequestDTO dto){
        return ResponseDTO.ok();
    }
}