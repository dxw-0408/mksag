package com.campus.assistant.dto;

import lombok.Data;

/** 更新知识条目请求（全字段可选）。 */
@Data
public class FaqUpdateRequest {

    private String category;
    private String question;
    private String answer;
    private String source;
    private String sourceUrl;
    private String tags;
    private Integer status;
}
