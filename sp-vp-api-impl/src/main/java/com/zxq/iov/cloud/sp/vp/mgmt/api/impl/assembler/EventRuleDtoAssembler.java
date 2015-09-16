/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-18       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler.EventRuleDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.mgmt.api.impl.assembler;

import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventRuleDto;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 事件规则传输对象装配类
 */
public class EventRuleDtoAssembler {

    public EventRule fromDto(final EventRuleDto eventRuleDto) {
        return new EventRule(eventRuleDto.getName(), eventRuleDto.getOperator(),eventRuleDto.getValue());
    }

    public List<EventRule> fromDtoList(final List<EventRuleDto> eventRuleDtos) {
        List<EventRule> eventRules = new ArrayList<>();
        for(EventRuleDto eventRuleDto : eventRuleDtos) {
            eventRules.add(fromDto(eventRuleDto));
        }
        return eventRules;
    }

}
