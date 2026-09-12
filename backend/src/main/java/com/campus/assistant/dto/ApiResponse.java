package com.campus.assistant.dto;

import com.campus.assistant.common.BusinessCode;
import lombok.Data;

/** 统一响应体：{code, message, data}，code=0 成功。 */
@Data
public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.setCode(BusinessCode.SUCCESS.getCode());
        resp.setMessage(BusinessCode.SUCCESS.getMessage());
        resp.setData(data);
        return resp;
    }

    public static <T> ApiResponse<T> ok() {
        return ok(null);
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }

    public static <T> ApiResponse<T> fail(BusinessCode businessCode) {
        return fail(businessCode.getCode(), businessCode.getMessage());
    }
}
