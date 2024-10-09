package com.pdp.pdp_crm.dto.member;

import com.pdp.pdp_crm.enums.CenterRole;
import com.pdp.pdp_crm.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long id;

    private Long userId;

    private Long centerId;

    private String firstName;

    private String lastName;

    private CenterRole role;

    private Long imageId;

    private EntityStatus entityStatus;
}
