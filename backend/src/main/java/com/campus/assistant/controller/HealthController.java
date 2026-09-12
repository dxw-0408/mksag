package com.campus.assistant.controller;

import com.campus.assistant.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** 健康检查。 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<Map<String, Object>> health() {
        return ApiResponse.ok(Map.<String, Object>of("status", "ok", "service", "campus-assistant"));
    }
}
