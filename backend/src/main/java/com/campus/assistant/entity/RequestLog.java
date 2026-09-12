package com.campus.assistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 请求日志。 */
@Data
@TableName("request_log")
public class RequestLog {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String method;
    private String path;
    private Integer statusCode;
    private Double latencyMs;
    private String clientIp;
    private LocalDateTime createdAt;
}
