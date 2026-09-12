package com.campus.assistant.service.rag;

import java.util.List;

/** 向量化：文本 -> 稠密向量。后续接入本地或云端 embedding 模型。 */
public interface Embedder {

    List<List<Double>> embed(List<String> texts);
}
