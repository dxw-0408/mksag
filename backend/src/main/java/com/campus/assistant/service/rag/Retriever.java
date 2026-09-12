package com.campus.assistant.service.rag;

import java.util.List;
import java.util.Map;

/** 混合检索 + Rerank：关键词(BM25) + 向量召回，再重排序。 */
public interface Retriever {

    record RetrievedChunk(String text, String source, double score, Map<String, Object> metadata) {}

    List<RetrievedChunk> retrieve(String query, int topK);
}
