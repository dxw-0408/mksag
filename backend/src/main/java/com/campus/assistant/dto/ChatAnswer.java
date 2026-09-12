package com.campus.assistant.dto;

import lombok.Data;

import java.util.List;

/** 问答结果。 */
@Data
public class ChatAnswer {

    private String conversationId;
    private String answer;
    private List<Source> sources = List.of();
    /** 无据可查时明确拒答（防幻觉）。 */
    private boolean refused;
    private long latencyMs;
}
