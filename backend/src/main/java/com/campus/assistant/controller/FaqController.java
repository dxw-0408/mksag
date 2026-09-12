package com.campus.assistant.controller;

import com.campus.assistant.dto.ApiResponse;
import com.campus.assistant.dto.FaqRequest;
import com.campus.assistant.dto.FaqUpdateRequest;
import com.campus.assistant.dto.FaqVO;
import com.campus.assistant.dto.PageResult;
import com.campus.assistant.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 知识库（校历 / 规章 / FAQ）。 */
@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping
    public ApiResponse<PageResult<FaqVO>> list(
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "20") long pageSize,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword) {
        return ApiResponse.ok(faqService.list(page, pageSize, category, keyword));
    }

    @PostMapping
    public ApiResponse<FaqVO> create(@RequestBody @Valid FaqRequest request) {
        return ApiResponse.ok(faqService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<FaqVO> get(@PathVariable Long id) {
        return ApiResponse.ok(faqService.get(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<FaqVO> update(@PathVariable Long id, @RequestBody FaqUpdateRequest request) {
        return ApiResponse.ok(faqService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        faqService.delete(id);
        return ApiResponse.ok();
    }
}
