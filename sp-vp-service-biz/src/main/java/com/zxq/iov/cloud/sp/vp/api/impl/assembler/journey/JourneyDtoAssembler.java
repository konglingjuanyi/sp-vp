package com.zxq.iov.cloud.sp.vp.api.impl.assembler.journey;

import com.zxq.iov.cloud.sp.vp.api.dto.journey.JourneyDto;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;

/**
 * 安防 行程传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-9 14:22
 * modify date 2015-6-11 17:47
 * @version 0.2, 2015-6-11
 */
public class JourneyDtoAssembler {

    public Journey fromDto(final JourneyDto journeyDto) {
        return new Journey(journeyDto.getTboxJourneyId(), journeyDto.getTboxId(), journeyDto.getOwnerId(),
                journeyDto.getUserId(), journeyDto.getKeyId(), journeyDto.getVin(), journeyDto.getStartTime(),
                journeyDto.getStartVehicleInfoId(), journeyDto.getEndTime(), journeyDto.getEndVehicleInfoId(),
                journeyDto.getDistance(), journeyDto.getAvgSpeed(), journeyDto.getFuelConsumption());
    }

    public JourneyDto toDto(final Journey journey) {
        return new JourneyDto(journey.getId(), journey.getTboxJourneyId(), journey.getTboxId(),
                journey.getOwnerId(), journey.getUserId(), journey.getKeyId(), journey.getVin(),
                journey.getStartTime(), journey.getStartVehicleInfoId(), journey.getEndTime(),
                journey.getEndVehicleInfoId(), journey.getDistance(), journey.getAvgSpeed(),
                journey.getFuelConsumption());
    }
}
