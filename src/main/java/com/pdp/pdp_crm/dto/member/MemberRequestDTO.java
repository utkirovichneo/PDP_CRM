package com.pdp.pdp_crm.dto.member;

import com.pdp.pdp_crm.enums.CenterRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {

    private Long userId;

    private Long centerId;

    private CenterRole role;

    private Long imageId;
}
