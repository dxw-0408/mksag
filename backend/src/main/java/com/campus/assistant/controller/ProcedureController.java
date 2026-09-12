package com.campus.assistant.controller;

import com.campus.assistant.dto.ApiResponse;
import com.campus.assistant.dto.ProcedureCreateRequest;
import com.campus.assistant.dto.ProcedureVO;
import com.campus.assistant.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 办事流程。 */
@RestController
@RequestMapping("/procedures")
@RequiredArgsConstructor
public class ProcedureController {

    private final ProcedureService procedureService;

    @GetMapping
    public ApiResponse<List<ProcedureVO>> list() {
        return ApiResponse.ok(procedureService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProcedureVO> get(@PathVariable Long id) {
        return ApiResponse.ok(procedureService.get(id));
    }

    @PostMapping
    public ApiResponse<ProcedureVO> create(@RequestBody ProcedureCreateRequest request) {
        return ApiResponse.ok(procedureService.create(request));
    }
}
