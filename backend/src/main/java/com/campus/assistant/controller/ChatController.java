package com.campus.assistant.controller;

import com.campus.assistant.dto.ApiResponse;
import com.campus.assistant.dto.ChatAnswer;
import com.campus.assistant.dto.ChatRequest;
import com.campus.assistant.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/** 智能问答。 */
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ApiResponse<ChatAnswer> chat(@RequestBody @Valid ChatRequest request) {
        return ApiResponse.ok(chatService.ask(request));
    }

    @GetMapping("/history")
    public ApiResponse<Map<String, Object>> history(@RequestParam String conversationId) {
        // TODO: 从 chat_message 表读取会话历史
        return ApiResponse.ok(Map.<String, Object>of("conversationId", conversationId, "messages", List.of()));
    }
}
