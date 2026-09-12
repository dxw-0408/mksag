package com.campus.assistant.controller;

import com.campus.assistant.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/** 工具调用：校历 / 图书馆 / 课表。 */
@RestController
@RequestMapping("/tools")
public class ToolsController {

    @GetMapping("/calendar")
    public ApiResponse<Map<String, Object>> calendar() {
        // TODO: 对接教务系统或从 faq(category=calendar) 读取
        return ApiResponse.ok(Map.<String, Object>of("semester", "2025-2026-1", "holidays", List.of()));
    }

    @GetMapping("/library/hours")
    public ApiResponse<Map<String, Object>> libraryHours() {
        // TODO: 对接图书馆开放时间接口
        return ApiResponse.ok(Map.<String, Object>of("open", "08:00", "close", "22:00"));
    }

    @GetMapping("/schedule")
    public ApiResponse<Map<String, Object>> schedule() {
        // TODO: 对接课表系统
        return ApiResponse.ok(Map.<String, Object>of("courses", List.of()));
    }
}
