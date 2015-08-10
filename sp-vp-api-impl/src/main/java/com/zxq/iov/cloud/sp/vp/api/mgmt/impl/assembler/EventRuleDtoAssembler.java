package com.zxq.iov.cloud.sp.vp.api.mgmt.impl.assembler;

import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventRuleDto;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 事件规则传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-18 10:59
 * modify date 2015-8-6 14:22
 * @version 0.2, 2015-8-6
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
