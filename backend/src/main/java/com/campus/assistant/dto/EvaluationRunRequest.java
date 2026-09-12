package com.campus.assistant.dto;

import lombok.Data;

/** 运行评测请求：量化检索召回与答案忠实度。 */
@Data
public class EvaluationRunRequest {

    private Integer limit = 100;
}
