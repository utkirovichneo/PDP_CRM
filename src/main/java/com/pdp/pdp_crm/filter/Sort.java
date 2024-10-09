package com.pdp.pdp_crm.filter;

import com.google.common.base.CaseFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sort {

    @Schema(description = "Field nomi", example = "id")
    private String name;

    @Schema(description = "Sortlash tartibi", example = "desc")
    private String direction;

    public String getName() {
        if(name.split("_").length == 1){
            return name;
        }
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }
}
