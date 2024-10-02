package com.pdp.pdp_crm.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pdp.pdp_crm.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequestDTO {

    private Long groupId;

    private Long studentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(defaultValue = "#{T(java.time.LocalDate).now()}")
    private LocalDate date;

    private AttendanceStatus status;
}
