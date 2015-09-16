/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.mgmt.api.impl.EventDefinitionApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.mgmt.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler.EventDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler.EventRuleDtoAssembler;
import com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler.StepDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler.TaskDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.constants.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import com.zxq.iov.cloud.sp.vp.mgmt.api.IEventDefinitionApi;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventDefinitionDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventRuleDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.StepDefinitionDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.TaskDefinitionDto;
import com.zxq.iov.cloud.sp.vp.service.IEventDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 安防服务 事件定义管理API实现类
 */
@Service
public class EventDefinitionApiImpl implements IEventDefinitionApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventDefinitionApiImpl.class);

	@Autowired
	private IEventDefinitionService eventDefinitionService;

	@Override
	public void createEventDefinition(EventDefinitionDto eventDefinitionDto) {
		EventDefinition eventDefinition = new EventDefinitionDtoAssembler().fromDto(eventDefinitionDto);
		eventDefinitionService.createEventDefinition(eventDefinition);
	}

	@Override
	public void createTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
		TaskDefinition taskDefinition = new TaskDefinitionDtoAssembler().fromDto(taskDefinitionDto);
		eventDefinitionService.createTaskDefinition(taskDefinition);
	}

	@Override
	public void createStepDefinition(StepDefinitionDto stepDefinitionDto) {
		StepDefinition stepDefinition = new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto);
		eventDefinitionService.createStepDefinition(stepDefinition);
	}

	@Override
	public void createStepDefinition(StepDefinitionDto stepDefinitionDto, List<EventRuleDto> eventRuleDtos) {
		eventDefinitionService.createStepDefinition(new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto),
				new EventRuleDtoAssembler().fromDtoList(eventRuleDtos));
	}

	@Override
	public EventDefinitionDto findEventDefinitionById(Long eventDefinitionId) throws ServLayerException {
		return new EventDefinitionDtoAssembler()
				.toDto(eventDefinitionService.findEventDefinitionById(eventDefinitionId));
	}

	@Override
	public TaskDefinitionDto findTaskDefinitionById(Long taskDefinitionId) throws ServLayerException {
		return new TaskDefinitionDtoAssembler().toDto(eventDefinitionService.findTaskDefinitionById(taskDefinitionId));
	}

	@Override
	public StepDefinitionDto findStepDefinitionById(Long stepDefinitionId) throws ServLayerException {
		return new StepDefinitionDtoAssembler().toDto(eventDefinitionService.findStepDefinitionById(stepDefinitionId));
	}

	@Override
	public List<TaskDefinitionDto> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId) {
		return new TaskDefinitionDtoAssembler()
				.toDtoList(eventDefinitionService.listTaskDefinitionByEventDefinitionId(eventDefinitionId));
	}

	@Override
	public List<StepDefinitionDto> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId) {
		return new StepDefinitionDtoAssembler()
				.toDtoList(eventDefinitionService.listStepDefinitionByTaskDefinitionId(taskDefinitionId));
	}

	@Override
	public void pagingEventDefinition(Map<String, Object> paramMap) {
		eventDefinitionService.pagingEventDefinition(paramMap);
	}

	@Override
	public void removeEventDefinition(Long eventDefinitionId) throws ServLayerException {
		if (listTaskDefinitionByEventDefinitionId(eventDefinitionId).size() > 0) {
			throw new ServLayerException(ExceptionConstants.HAS_CHILD);
		}
		eventDefinitionService.removeEventDefinition(eventDefinitionId);
	}

	@Override
	public void removeTaskDefinition(Long taskDefinitionId) throws ServLayerException {
		if (listStepDefinitionByTaskDefinitionId(taskDefinitionId).size() > 0) {
			throw new ServLayerException(ExceptionConstants.HAS_CHILD);
		}
		eventDefinitionService.removeTaskDefinition(taskDefinitionId);
	}

	@Override
	public void removeStepDefinition(Long stepDefinitionId) {
		eventDefinitionService.removeStepDefinition(stepDefinitionId);
	}

	@Override
	public void updateEventDefinition(EventDefinitionDto eventDefinitionDto) {
		eventDefinitionService.updateEventDefinition(new EventDefinitionDtoAssembler().fromDto(eventDefinitionDto));
	}

	@Override
	public void updateTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
		eventDefinitionService.updateTaskDefinition(new TaskDefinitionDtoAssembler().fromDto(taskDefinitionDto));
	}

	@Override
	public void updateStepDefinition(StepDefinitionDto stepDefinitionDto) {
		eventDefinitionService.updateStepDefinition(new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto));
	}
}