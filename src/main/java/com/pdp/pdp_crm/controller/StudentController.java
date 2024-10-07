package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/student")
public record StudentController(StudentService service) {
}
