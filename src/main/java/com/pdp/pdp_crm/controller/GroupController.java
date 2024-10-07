package com.pdp.pdp_crm.controller;

import com.pdp.pdp_crm.service.GroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/group")
public record GroupController(GroupService service) {
}
