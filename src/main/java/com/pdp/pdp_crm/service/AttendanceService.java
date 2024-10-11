package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.attendance.AttendanceRequestDTO;

public interface AttendanceService {
    AttendanceDTO create (AttendanceRequestDTO dto);
    AttendanceDTO findById(Long id);
    AttendanceDTO update (Long id, AttendanceRequestDTO dto);
}