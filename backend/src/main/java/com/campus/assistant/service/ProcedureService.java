package com.campus.assistant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.campus.assistant.dto.ProcedureCreateRequest;
import com.campus.assistant.dto.ProcedureStepVO;
import com.campus.assistant.dto.ProcedureVO;
import com.campus.assistant.entity.ProcedureStep;
import com.campus.assistant.entity.ServiceProcedure;
import com.campus.assistant.exception.BizException;
import com.campus.assistant.mapper.ProcedureStepMapper;
import com.campus.assistant.mapper.ServiceProcedureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** 办事流程服务。 */
@Service
@RequiredArgsConstructor
public class ProcedureService {

    private final ServiceProcedureMapper procedureMapper;
    private final ProcedureStepMapper stepMapper;

    public List<ProcedureVO> list() {
        List<ServiceProcedure> pros = procedureMapper.selectList(
                new LambdaQueryWrapper<ServiceProcedure>()
                        .orderByAsc(ServiceProcedure::getSort)
                        .orderByAsc(ServiceProcedure::getId));
        return toVOs(pros);
    }

    public ProcedureVO get(Long id) {
        ServiceProcedure pro = procedureMapper.selectById(id);
        if (pro == null) {
            throw BizException.notFound("流程 " + id + " 不存在");
        }
        return toVO(pro, stepsOf(pro.getId()));
    }

    public ProcedureVO create(ProcedureCreateRequest request) {
        ServiceProcedure pro = new ServiceProcedure();
        BeanUtils.copyProperties(request, pro);
        pro.setSort(0);
        procedureMapper.insert(pro);
        return get(pro.getId());
    }

    private List<ProcedureVO> toVOs(List<ServiceProcedure> pros) {
        if (pros.isEmpty()) {
            return List.of();
        }
        List<Long> ids = pros.stream().map(ServiceProcedure::getId).toList();
        Map<Long, List<ProcedureStep>> grouped = stepMapper.selectList(
                new LambdaQueryWrapper<ProcedureStep>()
                        .in(ProcedureStep::getProcedureId, ids)
                        .orderByAsc(ProcedureStep::getStepNo))
                .stream()
                .collect(Collectors.groupingBy(ProcedureStep::getProcedureId));
        return pros.stream()
                .map(p -> toVO(p, grouped.getOrDefault(p.getId(), List.of())))
                .toList();
    }

    private List<ProcedureStep> stepsOf(Long procedureId) {
        return stepMapper.selectList(
                new LambdaQueryWrapper<ProcedureStep>()
                        .eq(ProcedureStep::getProcedureId, procedureId)
                        .orderByAsc(ProcedureStep::getStepNo));
    }

    private ProcedureVO toVO(ServiceProcedure pro, List<ProcedureStep> steps) {
        ProcedureVO vo = new ProcedureVO();
        BeanUtils.copyProperties(pro, vo);
        vo.setSteps(steps.stream().map(this::toStepVO).toList());
        return vo;
    }

    private ProcedureStepVO toStepVO(ProcedureStep step) {
        ProcedureStepVO vo = new ProcedureStepVO();
        BeanUtils.copyProperties(step, vo);
        return vo;
    }
}
