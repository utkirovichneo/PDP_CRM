package com.pdp.pdp_crm.dto.member;

import com.pdp.pdp_crm.enums.CenterRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {

    private String phoneNumber;

    private String password;

    private Long centerId;

    private String firstName;

    private String lastName;

    private CenterRole role;

}
