package com.pdp.pdp_crm.dto.student;

import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.enums.Gender;
import com.pdp.pdp_crm.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Gender gender;

    private StudentStatus status;

    private EntityStatus entityStatus;

    private Long groupId;
}
