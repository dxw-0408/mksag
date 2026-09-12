package com.campus.assistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 评测结果：检索召回率、答案忠实度等。 */
@Data
@TableName("evaluation_result")
public class EvaluationResult {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long evalId;
    private String question;
    private Double recall;
    private Double faithfulness;
    private Double latencyMs;
    private LocalDateTime createdAt;
}
