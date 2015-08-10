package com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;

/**
 * 安防 被盗警报传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-15 13:07
 * modify date 2015-6-26 10:36
 * @version 0.2, 2015-6-26
 */
public class StolenAlarmDtoAssembler {

    public StolenAlarm fromDto(final StolenAlarmDto stolenAlarmDto) {
        return new StolenAlarm(stolenAlarmDto.getAlarmTypeId(),
                stolenAlarmDto.getAlarmData(), stolenAlarmDto.getAlarmTime());
    }

}
