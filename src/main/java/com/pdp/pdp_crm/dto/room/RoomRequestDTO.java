package com.pdp.pdp_crm.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {
    private String name;

    private String number;

    private Long capacity;

    private String definition;
}