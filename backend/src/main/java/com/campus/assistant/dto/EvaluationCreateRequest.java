package com.campus.assistant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** 新增评测条目请求。 */
@Data
public class EvaluationCreateRequest {

    @NotBlank(message = "问题不能为空")
    private String question;

    @NotBlank(message = "参考答案不能为空")
    private String referenceAnswer;

    private String category = "faq";
}
