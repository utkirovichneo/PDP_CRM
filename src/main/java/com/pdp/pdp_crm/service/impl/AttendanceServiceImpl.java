package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.AttendanceRepository;
import com.pdp.pdp_crm.service.AttendanceService;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
}