package com.pdp.pdp_crm.dto.teacher;

import com.pdp.pdp_crm.enums.CenterRole;
import com.pdp.pdp_crm.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private Long id;

    private Long userId;

    private Long centerId;

    private String firstName;

    private String lastName;

    private CenterRole role;

    private EntityStatus entityStatus;

}
