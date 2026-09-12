package com.campus.assistant.dto;

import lombok.Data;

/** 流程步骤返回。 */
@Data
public class ProcedureStepVO {

    private Long id;
    private Integer stepNo;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String tip;
}
