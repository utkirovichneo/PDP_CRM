package com.pdp.pdp_crm.dto.address;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;

    private String city;

    private String street;

    private String number;

    private String description;
}
