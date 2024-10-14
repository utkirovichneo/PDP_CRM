package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

public interface AttendanceService {

    Page<AttendanceDTO> filter(Long teacherId, Long groupId, PageableRequest pageableRequest);

}