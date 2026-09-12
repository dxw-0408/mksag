package com.campus.assistant.dto;

import lombok.Data;

/** 评测条目返回。 */
@Data
public class EvaluationVO {

    private Long id;
    private String question;
    private String referenceAnswer;
    private String category;
}
