package com.campus.assistant.controller;

import com.campus.assistant.dto.ApiResponse;
import com.campus.assistant.dto.EvaluationCreateRequest;
import com.campus.assistant.dto.EvaluationRunRequest;
import com.campus.assistant.dto.EvaluationRunResult;
import com.campus.assistant.dto.EvaluationVO;
import com.campus.assistant.entity.EvaluationSet;
import com.campus.assistant.mapper.EvaluationSetMapper;
import com.campus.assistant.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 评测集。 */
@RestController
@RequestMapping("/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationSetMapper evaluationSetMapper;
    private final EvaluationService evaluationService;

    @GetMapping
    public ApiResponse<List<EvaluationVO>> list() {
        List<EvaluationVO> items = evaluationSetMapper.selectList(null).stream().map(this::toVO).toList();
        return ApiResponse.ok(items);
    }

    @PostMapping
    public ApiResponse<EvaluationVO> create(@RequestBody @Valid EvaluationCreateRequest request) {
        EvaluationSet set = new EvaluationSet();
        BeanUtils.copyProperties(request, set);
        evaluationSetMapper.insert(set);
        return ApiResponse.ok(toVO(set));
    }

    @PostMapping("/run")
    public ApiResponse<EvaluationRunResult> run(@RequestBody EvaluationRunRequest request) {
        return ApiResponse.ok(evaluationService.run(request));
    }

    private EvaluationVO toVO(EvaluationSet set) {
        EvaluationVO vo = new EvaluationVO();
        BeanUtils.copyProperties(set, vo);
        return vo;
    }
}
