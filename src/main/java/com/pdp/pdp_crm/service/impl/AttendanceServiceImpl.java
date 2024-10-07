package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.AttendanceRepository;
import com.pdp.pdp_crm.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
}