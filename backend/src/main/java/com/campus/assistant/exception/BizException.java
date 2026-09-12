package com.campus.assistant.exception;

import com.campus.assistant.common.BusinessCode;
import lombok.Getter;

/** 业务异常。 */
@Getter
public class BizException extends RuntimeException {

    private final int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(BusinessCode businessCode) {
        super(businessCode.getMessage());
        this.code = businessCode.getCode();
    }

    public static BizException notFound(String message) {
        return new BizException(BusinessCode.NOT_FOUND.getCode(), message);
    }
}
