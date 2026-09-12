package com.campus.assistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/** 办事流程步骤。 */
@Data
@TableName("procedure_step")
public class ProcedureStep {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long procedureId;
    private Integer stepNo;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String tip;
}
