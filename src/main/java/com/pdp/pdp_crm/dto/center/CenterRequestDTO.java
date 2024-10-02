package com.pdp.pdp_crm.dto.center;

import com.pdp.pdp_crm.dto.address.AddressRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterRequestDTO {
    @NotBlank(message = "Center name is not blank")
    private String name;

    private String legalName;

    private AddressRequestDTO address;

    private MultipartFile image;

    private String phone;

    private String email;

    private String description;

    private Long userId;
}
