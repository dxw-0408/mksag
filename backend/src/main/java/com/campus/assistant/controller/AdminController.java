package com.campus.assistant.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.campus.assistant.dto.ApiResponse;
import com.campus.assistant.entity.RequestLog;
import com.campus.assistant.mapper.RequestLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/** 管理后台：统计 / 日志。 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final RequestLogMapper requestLogMapper;

    @GetMapping("/stats")
    public ApiResponse<Map<String, Object>> stats() {
        // TODO: 统计热点问答、用户量、问题未解决率
        return ApiResponse.ok(Map.<String, Object>of(
                "hotQuestions", List.of(), "totalUsers", 0, "unresolvedRate", 0.0));
    }

    @GetMapping("/logs")
    public ApiResponse<List<Map<String, Object>>> logs(@RequestParam(defaultValue = "100") int limit) {
        int bounded = Math.min(Math.max(limit, 1), 1000);
        List<RequestLog> rows = requestLogMapper.selectList(
                new LambdaQueryWrapper<RequestLog>()
                        .orderByDesc(RequestLog::getId)
                        .last("LIMIT " + bounded));
        List<Map<String, Object>> data = rows.stream().map(r -> Map.<String, Object>of(
                "id", r.getId(),
                "method", r.getMethod(),
                "path", r.getPath(),
                "statusCode", r.getStatusCode(),
                "latencyMs", r.getLatencyMs(),
                "clientIp", r.getClientIp() == null ? "" : r.getClientIp(),
                "createdAt", r.getCreatedAt() == null ? "" : r.getCreatedAt().format(FMT)
        )).toList();
        return ApiResponse.ok(data);
    }
}
