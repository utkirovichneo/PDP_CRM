package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.service.FinanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/finance")
public record FinanceController(FinanceService service) {
}