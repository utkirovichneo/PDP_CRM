package com.pdp.pdp_crm.dto.room;

import com.pdp.pdp_crm.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {

    private Long centerId;

    private String name;

    private String number;

    private Long capacity;

    private String definition;

    private EntityStatus entityStatus;
}