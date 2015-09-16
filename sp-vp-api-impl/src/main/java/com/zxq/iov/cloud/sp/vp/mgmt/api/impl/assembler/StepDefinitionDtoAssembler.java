/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-04       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler.StepDefinitionDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler;

import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.StepDefinitionDto;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 步骤定义传输对象装配类
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
