package com.pdp.pdp_crm.exception;

import com.pdp.pdp_crm.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private ErrorCode errorCode;
    private String tableName;

    public NotFoundException(String tableName) {
        super(tableName + " not found!");
        this.tableName = tableName;
    }

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getCode().toString());
        this.errorCode = errorCode;
    }
}
