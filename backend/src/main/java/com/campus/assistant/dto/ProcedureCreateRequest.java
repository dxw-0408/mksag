package com.campus.assistant.dto;

import lombok.Data;

/** 新增办事流程请求。 */
@Data
public class ProcedureCreateRequest {

    private String title;
    private String category;
    private String description;
}
