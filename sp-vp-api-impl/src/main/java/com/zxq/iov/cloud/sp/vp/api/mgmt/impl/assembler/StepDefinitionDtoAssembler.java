package com.zxq.iov.cloud.sp.vp.api.mgmt.impl.assembler;

import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.StepDefinitionDto;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 步骤定义传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-4 10:58
 * modify date
 * @version 0.1, 2015-6-4
 */
public class StepDefinitionDtoAssembler {

    public StepDefinition fromDto(final StepDefinitionDto stepDefinitionDto) {
        return new StepDefinition(stepDefinitionDto.getTaskDefinitionId(), stepDefinitionDto.getName(),
                stepDefinitionDto.getLifecycle(), stepDefinitionDto.getPreStepDefinitionId(),
                stepDefinitionDto.getRetryLimit(), stepDefinitionDto.getStartCode(),
                stepDefinitionDto.isRollback(), stepDefinitionDto.isLast(), stepDefinitionDto.getSort());
    }

    public StepDefinitionDto toDto(final StepDefinition stepDefinition) {
        return new StepDefinitionDto(stepDefinition.getId(), stepDefinition.getTaskDefinitionId(),
                stepDefinition.getName(), stepDefinition.getLifecycle(), stepDefinition.getPreStepDefinitionId(),
                stepDefinition.getRetryLimit(), stepDefinition.getStartCode(), stepDefinition.isRollback(),
                stepDefinition.isLast(), stepDefinition.getSort());
    }

    public List<StepDefinitionDto> toDtoList(final List<StepDefinition> stepDefinitions) {
        List<StepDefinitionDto> stepDefinitionDtos = new ArrayList<>();
        for(StepDefinition stepDefinition : stepDefinitions) {
            stepDefinitionDtos.add(toDto(stepDefinition));
        }
        return stepDefinitionDtos;
    }
}
