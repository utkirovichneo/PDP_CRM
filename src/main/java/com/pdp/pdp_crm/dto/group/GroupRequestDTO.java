package com.pdp.pdp_crm.dto.group;

import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.enums.GroupDays;
import com.pdp.pdp_crm.enums.GroupStatus;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequestDTO {

    private String groupName;

    private Long courseId;

    private Long teacherId;

    private Long roomId;

    private List<GroupDays> days;

    private LocalTime startTime;

    private LocalTime endTime;

    private GroupStatus status;

    private EntityStatus entityStatus;
}
