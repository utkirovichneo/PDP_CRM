package com.tolik.pdp_crm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDataResponse {
    private Integer errorCode;
    private String errorMessage;
    private String details;
    private List<ErrorDetailDTO> fieldErrors;
}
