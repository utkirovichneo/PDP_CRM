package com.pdp.pdp_crm.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageableRequest {

    @Schema(description = "perPage", example = "10")
    private int perPage = 10;

    @Schema(description = "page", example = "0")
    private int page = 0;

    @Schema(description = "Sort ma'lumotlari")
    private Sort sort = new Sort();

    @Valid
    private List<SearchCriteria> search;
}
