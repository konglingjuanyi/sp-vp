package com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;

/**
 * 安防 被盗警报传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-15 13:07
 * modify date
 * @version 0.1, 2015-6-15
 */
public class StolenAlarmDtoAssembler {

    public StolenAlarm fromDto(final StolenAlarmDto stolenAlarmDto) {
        return new StolenAlarm(stolenAlarmDto.getTboxId(), stolenAlarmDto.getAlarmType(),
                stolenAlarmDto.getAlarmData(), stolenAlarmDto.getAlarmTime());
    }

    public StolenAlarmDto toDto(final StolenAlarm stolenAlarm) {
        return new StolenAlarmDto(stolenAlarm.getId(),stolenAlarm.getTboxId(), stolenAlarm.getAlarmType(),
                stolenAlarm.getAlarmData(), stolenAlarm.getAlarmTime());
    }
}
