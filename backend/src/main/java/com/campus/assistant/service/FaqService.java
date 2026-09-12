package com.campus.assistant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.assistant.dto.FaqRequest;
import com.campus.assistant.dto.FaqUpdateRequest;
import com.campus.assistant.dto.FaqVO;
import com.campus.assistant.dto.PageResult;
import com.campus.assistant.entity.Faq;
import com.campus.assistant.exception.BizException;
import com.campus.assistant.mapper.FaqMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/** 知识库服务。 */
@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    public PageResult<FaqVO> list(long page, long pageSize, String category, String keyword) {
        LambdaQueryWrapper<Faq> qw = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(category)) {
            qw.eq(Faq::getCategory, category);
        }
        if (StringUtils.hasText(keyword)) {
            qw.and(w -> w.like(Faq::getQuestion, keyword).or().like(Faq::getAnswer, keyword));
        }
        qw.orderByDesc(Faq::getId);

        Page<Faq> result = faqMapper.selectPage(new Page<>(page, pageSize), qw);
        List<FaqVO> items = result.getRecords().stream().map(this::toVO).toList();
        return new PageResult<>(result.getTotal(), items, page, pageSize);
    }

    public FaqVO get(Long id) {
        Faq faq = faqMapper.selectById(id);
        if (faq == null) {
            throw BizException.notFound("FAQ " + id + " 不存在");
        }
        return toVO(faq);
    }

    public FaqVO create(FaqRequest request) {
        Faq faq = new Faq();
        BeanUtils.copyProperties(request, faq);
        faq.setStatus(1);
        faqMapper.insert(faq);
        return get(faq.getId());
    }

    public FaqVO update(Long id, FaqUpdateRequest request) {
        Faq faq = faqMapper.selectById(id);
        if (faq == null) {
            throw BizException.notFound("FAQ " + id + " 不存在");
        }
        if (StringUtils.hasText(request.getCategory())) {
            faq.setCategory(request.getCategory());
        }
        if (StringUtils.hasText(request.getQuestion())) {
            faq.setQuestion(request.getQuestion());
        }
        if (StringUtils.hasText(request.getAnswer())) {
            faq.setAnswer(request.getAnswer());
        }
        if (request.getSource() != null) {
            faq.setSource(request.getSource());
        }
        if (request.getSourceUrl() != null) {
            faq.setSourceUrl(request.getSourceUrl());
        }
        if (request.getTags() != null) {
            faq.setTags(request.getTags());
        }
        if (request.getStatus() != null) {
            faq.setStatus(request.getStatus());
        }
        faqMapper.updateById(faq);
        return get(id);
    }

    public void delete(Long id) {
        if (faqMapper.selectById(id) == null) {
            throw BizException.notFound("FAQ " + id + " 不存在");
        }
        faqMapper.deleteById(id);
    }

    private FaqVO toVO(Faq faq) {
        FaqVO vo = new FaqVO();
        BeanUtils.copyProperties(faq, vo);
        return vo;
    }
}
