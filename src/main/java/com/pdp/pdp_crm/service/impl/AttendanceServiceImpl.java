package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.dto.attendance.AttendanceRequestDTO;
import com.pdp.pdp_crm.entity.Address;
import com.pdp.pdp_crm.entity.Attendance;
import com.pdp.pdp_crm.enums.AttendanceStatus;
import com.pdp.pdp_crm.mapper.AttendanceMapper;
import com.pdp.pdp_crm.repository.AttendanceRepository;
import com.pdp.pdp_crm.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final GroupServiceImpl groupServiceImpl;
    private final StudentServiceImpl studentServiceImpl;

    @Override
    public AttendanceDTO create(AttendanceRequestDTO dto) {
        return attendanceMapper.toDto(
                attendanceRepository.save(
                        Attendance.builder()
                                .student(studentServiceImpl.findByIdOptional(dto.getStudentId()).orElseThrow(RuntimeException::new))
                                .date(dto.getDate())
                                .status(AttendanceStatus.PARTICIPATED)
                                .build()
                ));
    }

    @Override
    public AttendanceDTO findById(Long id) {
        return attendanceMapper.toDto(attendanceRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public AttendanceDTO update(Long id, AttendanceRequestDTO dto) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(RuntimeException::new);
        attendance.setStudent(studentServiceImpl.findByIdOptional(id).orElseThrow(RuntimeException::new));
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());
        return attendanceMapper.toDto(attendanceRepository.save(attendance));
    }
}