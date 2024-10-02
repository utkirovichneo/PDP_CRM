package com.pdp.pdp_crm.dto.group;

import com.pdp.pdp_crm.enums.GroupDays;
import com.pdp.pdp_crm.enums.GroupStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

    private Long id;

    private String groupName;

    private Long centerId;

    private Long courseId;

    private Long teacherId;

    private Long roomId;

    private List<GroupDays> days;

    private LocalTime startTime;

    private LocalTime endTime;

    private GroupStatus status;
}
