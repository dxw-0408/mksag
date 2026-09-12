package com.campus.assistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 办事流程：报到、缴费、助学贷款办理等。 */
@Data
@TableName("service_procedure")
public class ServiceProcedure {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String category;
    private String description;
    private Integer sort;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
