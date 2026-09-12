package com.campus.assistant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** 新增知识条目请求。 */
@Data
public class FaqRequest {

    private String category = "faq";

    @NotBlank(message = "问题不能为空")
    private String question;

    @NotBlank(message = "答案不能为空")
    private String answer;

    private String source;
    private String sourceUrl;
    private String tags;
}
