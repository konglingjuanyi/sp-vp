package com.zxq.iov.cloud.sp.vp.api.impl.assembler.event;

import com.zxq.iov.cloud.sp.vp.api.dto.event.EventDefinitionDto;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 事件定义传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-4 10:21
 * modify date
 * @version 0.1, 2015-6-4
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
