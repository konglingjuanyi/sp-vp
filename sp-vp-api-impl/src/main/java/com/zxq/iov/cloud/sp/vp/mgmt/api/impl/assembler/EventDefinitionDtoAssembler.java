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
 * com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler.EventDefinitionDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler;

import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventDefinitionDto;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 事件定义传输对象装配类
 */
public class EventDefinitionDtoAssembler {

    public EventDefinition fromDto(final EventDefinitionDto eventDefinitionDto) {
        return new EventDefinition(eventDefinitionDto.getName(), eventDefinitionDto.getType(),
                eventDefinitionDto.getLifecycle(), eventDefinitionDto.isExclusive(),
                eventDefinitionDto.isContinue(), eventDefinitionDto.isRollback());
    }

    public EventDefinitionDto toDto(final EventDefinition eventDefinition) {
        return new EventDefinitionDto(eventDefinition.getId(), eventDefinition.getName(),
                eventDefinition.getType(), eventDefinition.getLifecycle(), eventDefinition.isExclusive(),
                eventDefinition.isContinue(), eventDefinition.isRollback());
    }

    public List<EventDefinitionDto> toDtoList(final List<EventDefinition> eventDefinitions) {
        List<EventDefinitionDto> eventDefinitionDtos = new ArrayList<>();
        for(EventDefinition eventDefinition : eventDefinitions) {
            eventDefinitionDtos.add(toDto(eventDefinition));
        }
        return eventDefinitionDtos;
    }
}
