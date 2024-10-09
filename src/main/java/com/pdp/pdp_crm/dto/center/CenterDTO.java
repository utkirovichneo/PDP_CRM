package com.pdp.pdp_crm.dto.center;

import com.pdp.pdp_crm.dto.address.AddressDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import lombok.*;

@Getter
@Setter
public class CenterDTO {

    private Long id;

    private String name;

    private String legalName;

    private AddressDTO address;

    private String logo;

    private String phone;

    private String email;

    private String description;

    private UserResponseDTO user;

}