package com.pdp.pdp_crm.dto.attendance;

import com.pdp.pdp_crm.enums.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

    private Long id;

    private Long groupId;

    private Long studentId;

    private LocalDate date;

    private AttendanceStatus status;
}