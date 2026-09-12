package com.campus.assistant.service;

import com.campus.assistant.dto.EvaluationRunRequest;
import com.campus.assistant.dto.EvaluationRunResult;
import org.springframework.stereotype.Service;

/** 评测服务：遍历自建 FAQ 评测集，量化检索召回与答案忠实度。 */
@Service
public class EvaluationService {

    public EvaluationRunResult run(EvaluationRunRequest request) {
        // TODO: 读取 evaluation_set，调用 RAG 检索与大模型，计算 recall / faithfulness
        EvaluationRunResult result = new EvaluationRunResult();
        result.setTotal(request.getLimit() == null ? 100 : request.getLimit());
        result.setRecall(0.0);
        result.setFaithfulness(0.0);
        return result;
    }
}
