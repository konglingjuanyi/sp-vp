package com.zxq.iov.cloud.sp.vp.api.impl.assembler.event;

import com.zxq.iov.cloud.sp.vp.api.dto.event.EventRuleDto;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;

/**
 * 安防 事件规则传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-18 10:59
 * modify date
 * @version 0.1, 2015-6-18
 */
public class EventRuleDtoAssembler {

    public EventRule fromDto(final EventRuleDto eventRuleDto) {
        return new EventRule(eventRuleDto.getName(), eventRuleDto.getOperator(),eventRuleDto.getValue());
    }

}
