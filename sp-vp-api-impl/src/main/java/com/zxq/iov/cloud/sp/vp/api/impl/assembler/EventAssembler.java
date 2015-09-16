/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-08-11       荣杰         1.0            Initial Version
 * 2015-08-12       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;

/**
 * 安防服务 事件对象装配类
 */
public class EventAssembler {

    public Event fromOtaDto(final OtaDto otaDto) {
        String vin = otaDto.getVin();
        if(StringUtils.isBlank(vin)) {
            vin = "11111111111111111"; // 此处VIN应该通过TBOX ID获取
        }
        return new Event(otaDto.getEventId(), vin, otaDto.getAid()+otaDto.getMid());
    }

}
