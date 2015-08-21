package com.zxq.iov.cloud.sp.vp.api.impl.assembler;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;

/**
 * 安防 事件对象装配类
 *
 * @author 叶荣杰
 * create date 2015-8-11 15:35
 * modify date 2015-8-12 10:00
 * @version 0.2, 2015-8-12
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
