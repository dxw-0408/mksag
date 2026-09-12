package com.campus.assistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 知识条目：校历(calendar) / 规章(regulation) / 常见问答(faq)。 */
@Data
@TableName("faq")
public class Faq {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String category;
    private String question;
    private String answer;
    private String source;
    private String sourceUrl;
    private String tags;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
