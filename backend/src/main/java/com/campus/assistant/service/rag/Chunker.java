package com.campus.assistant.service.rag;

import java.util.List;
import java.util.Map;

/** 文本分块：按语义 / 固定长度切分，为向量化与检索做准备。 */
public interface Chunker {

    record Chunk(String text, String source, Map<String, Object> metadata) {}

    List<Chunk> split(String text, int chunkSize, int overlap);
}
