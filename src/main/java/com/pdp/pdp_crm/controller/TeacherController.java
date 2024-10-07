package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public record TeacherController(TeacherService service) {
}
