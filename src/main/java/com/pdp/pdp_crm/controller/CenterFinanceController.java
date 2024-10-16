package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.dto.finance.FinanceDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeRequestDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
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
        return ResponseDTO.ok(service.getOne(centerId));
    }

    @PostMapping("/income/filter")
    public ResponseEntity<ResponseDTO<List<FinanceIncomeDTO>>> filterIncome(@RequestParam Long centerId,
                                                                            @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAllIncomeFinance(centerId, pageableRequest));
    }

    @GetMapping("/income/{id}")
    public ResponseEntity<ResponseDTO<FinanceIncomeDTO>> getFinanceIncome(@RequestParam Long centerId,
                                                                          @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.findOneIncomeFinance(centerId, id));
    }

    @PostMapping("/outcome/filter")
    public ResponseEntity<ResponseDTO<List<FinanceOutcomeDTO>>> filterOutcome(@RequestParam Long centerId,
                                                                              @RequestBody PageableRequest pageableRequest){
        return ResponseDTO.page(service.findAllOutcomeFinance(centerId, pageableRequest));
    }

    @GetMapping("/outcome/{id}")
    public ResponseEntity<ResponseDTO<FinanceOutcomeDTO>> getFinanceOutcome(@RequestParam Long centerId,
                                                                            @PathVariable(name = "id") Long id){
        return ResponseDTO.ok(service.findOneOutcomeFinance(centerId, id));
    }

    //TODO
    @PostMapping("/expense")
    public ResponseEntity<ResponseDTO<FinanceOutcomeDTO>> expense(@RequestParam Long centerId,
                                                                  @RequestBody FinanceOutcomeRequestDTO dto){
        return ResponseDTO.ok();
    }
}