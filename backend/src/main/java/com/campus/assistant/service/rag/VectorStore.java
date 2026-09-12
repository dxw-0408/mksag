package com.campus.assistant.service.rag;

import java.util.List;
import java.util.Map;

/** 向量库：存储与相似度检索。后续接入 Chroma / Milvus / pgvector。 */
public interface VectorStore {

    record SearchHit(String chunkId, String text, double score, Map<String, Object> metadata) {}

    void add(String chunkId, List<Double> vector, String text, Map<String, Object> metadata);

    List<SearchHit> search(List<Double> vector, int topK);
}
