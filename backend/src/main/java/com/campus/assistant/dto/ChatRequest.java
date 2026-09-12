package com.campus.assistant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** 问答请求。 */
@Data
public class ChatRequest {

    private String conversationId;

    @NotBlank(message = "问题不能为空")
    private String question;

    // history: 多轮对话历史，预留，后续补充
}
