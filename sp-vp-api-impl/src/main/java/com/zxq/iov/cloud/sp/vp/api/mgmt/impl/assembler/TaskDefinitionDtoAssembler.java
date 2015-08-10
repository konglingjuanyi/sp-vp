package com.zxq.iov.cloud.sp.vp.api.mgmt.impl.assembler;

import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.TaskDefinitionDto;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 任务定义传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-4 10:39
 * modify date
 * @version 0.1, 2015-6-4
 */
public class TaskDefinitionDtoAssembler {

    public TaskDefinition fromDto(final TaskDefinitionDto taskDefinitionDto) {
        return new TaskDefinition(taskDefinitionDto.getEventDefinitionId(), taskDefinitionDto.getName(),
                taskDefinitionDto.getLifecycle(), taskDefinitionDto.getPreTaskDefinitionId(),
                taskDefinitionDto.getCycleLimit(), taskDefinitionDto.isExclusive(), taskDefinitionDto.isContinue(),
                taskDefinitionDto.isRollback(), taskDefinitionDto.isLast(), taskDefinitionDto.getSort());
    }

    public TaskDefinitionDto toDto(final TaskDefinition taskDefinition) {
        return new TaskDefinitionDto(taskDefinition.getId(), taskDefinition.getEventDefinitionId(),
                taskDefinition.getName(), taskDefinition.getLifecycle(), taskDefinition.getPreTaskDefinitionId(),
                taskDefinition.getCycleLimit(), taskDefinition.isExclusive(), taskDefinition.isContinue(),
                taskDefinition.isRollback(), taskDefinition.isLast(), taskDefinition.getSort());
    }

    public List<TaskDefinitionDto> toDtoList(final List<TaskDefinition> taskDefinitions) {
        List<TaskDefinitionDto> taskDefinitionDtos = new ArrayList<>();
        for(TaskDefinition taskDefinition : taskDefinitions) {
            taskDefinitionDtos.add(toDto(taskDefinition));
        }
        return taskDefinitionDtos;
    }
}
