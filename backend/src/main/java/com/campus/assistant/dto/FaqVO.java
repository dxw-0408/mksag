package com.campus.assistant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/** 知识条目返回。 */
@Data
public class FaqVO {

    private Long id;
    private String category;
    private String question;
    private String answer;
    private String source;
    private String sourceUrl;
    private String tags;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
