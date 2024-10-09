package com.pdp.pdp_crm.dto.lessonavailable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonAvailableDTO {

    private Long id;

    private Long groupId;

    private LocalDate date;

    private Boolean isLessonAvailable;
}
