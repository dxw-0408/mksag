package com.campus.assistant.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/** 办事流程返回。 */
@Data
public class ProcedureVO {

    private Long id;
    private String title;
    private String category;
    private String description;
    private List<ProcedureStepVO> steps = new ArrayList<>();
}
