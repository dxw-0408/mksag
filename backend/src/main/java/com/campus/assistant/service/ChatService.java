package com.campus.assistant.service;

import com.campus.assistant.dto.ChatAnswer;
import com.campus.assistant.dto.ChatRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 问答编排：检索 → 生成 → 来源引用 → 拒答兜底。
 * 脚手架阶段返回占位答案；后续接入 service.rag 的管线：
 * 1. Retriever.retrieve(question) -> 候选段落
 * 2. LlmClient.chat(...) -> 带来源引用的答案
 * 3. 无相关上下文时明确拒答 refused=true（防幻觉）
 */
@Service
public class ChatService {

    public ChatAnswer ask(ChatRequest request) {
        String conversationId = (request.getConversationId() == null || request.getConversationId().isBlank())
                ? UUID.randomUUID().toString().replace("-", "")
                : request.getConversationId();

        // TODO: 接入 RAG 检索与 LLM 生成，并落库 chat_session / chat_message
        ChatAnswer answer = new ChatAnswer();
        answer.setConversationId(conversationId);
        answer.setAnswer("（脚手架占位）知识库与大模型尚未接入，暂时无法回答。");
        answer.setSources(List.of());
        answer.setRefused(true);
        return answer;
    }
}
