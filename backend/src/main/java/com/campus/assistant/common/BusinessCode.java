package com.campus.assistant.common;

/** 业务错误码。code=0 成功，非 0 为业务错误。 */
public enum BusinessCode {

    SUCCESS(0, "success"),
    BAD_REQUEST(40001, "参数错误"),
    NOT_FOUND(40401, "资源不存在"),
    RATE_LIMIT(42901, "请求过于频繁，请稍后再试"),
    INTERNAL_ERROR(50000, "服务器内部错误");

    private final int code;
    private final String message;

    BusinessCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
