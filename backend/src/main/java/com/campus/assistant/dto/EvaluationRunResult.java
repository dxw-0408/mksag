package com.campus.assistant.dto;

import lombok.Data;

/** 评测结果。 */
@Data
public class EvaluationRunResult {

    private int total;
    private double recall;
    private double faithfulness;
}
