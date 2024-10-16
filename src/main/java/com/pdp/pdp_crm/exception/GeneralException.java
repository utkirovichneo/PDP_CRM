package com.pdp.pdp_crm.exception;

import com.pdp.pdp_crm.exception.enums.ErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private ErrorCode errorCode;

    @Deprecated
    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getCode().toString());
        this.errorCode = errorCode;
    }
}
