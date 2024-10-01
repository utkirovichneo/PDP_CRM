package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Center extends BaseEntity {

    private String name;    // Nomi

    private String legalName;   // Qonuniy nomi

    private Address address;

    private Image logo;

    private String phone;

    private String email;

    private String description;

    private User user;
}