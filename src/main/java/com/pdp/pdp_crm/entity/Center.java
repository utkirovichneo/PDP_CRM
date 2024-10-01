package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String name;    // Nomi

    private String legalName;   // Qonuniy nomi

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "logo_id")
    private Image logo;

    private String phone;

    private String email;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}