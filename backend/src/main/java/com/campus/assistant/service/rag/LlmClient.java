package com.campus.assistant.service.rag;

import java.util.List;
import java.util.Map;

/** 大模型 API 封装：对话生成、带来源引用的答案生成。 */
public interface LlmClient {

    String chat(List<Map<String, String>> messages);
}
