package com.campus.assistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 自建校园 FAQ 评测集（验收要求 >= 100 条）。 */
@Data
@TableName("evaluation_set")
public class EvaluationSet {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String question;
    private String referenceAnswer;
    private String category;
    private LocalDateTime createdAt;
}
