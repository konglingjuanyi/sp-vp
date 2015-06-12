package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IJourneyService;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.journey.JourneyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.exception.TboxJourneyIdNotFindException;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.journey.JourneyDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDaoService;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 行程服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-9 14:19
 * modify date 2015-6-12 9:19
 * @version 0.4, 2015-6-12
 */
@Service
@Qualifier("journeyService")
public class JourneyServiceImpl implements IJourneyService {

    @Autowired
    private IJourneyDaoService journeyDaoService;
    @Autowired
    private IStatusService statusService;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public void startJourney(JourneyDto journeyDto) {
        Journey journey = findJourneyByTboxJourneyIdAndTboxId(journeyDto.getTboxJourneyId(), journeyDto.getTboxId());
        if(null == journey) {
            journey = new JourneyDtoAssembler().fromDto(journeyDto);
            journey.setTboxId(journeyDto.getTboxId());
            journey.setStatus(RUNNING_STATUS);
            journeyDaoService.createJourney(journey);
        }
        else {
            journey.setStartTime(journeyDto.getStartTime());
            journey.setKeyId(journeyDto.getKeyId());
            journeyDaoService.updateJourney(journey);
        }
    }

    @Override
    public void updateJourney(JourneyDto journeyDto, VehicleInfoDto vehicleInfoDto) {
        Journey journey = findJourneyByTboxJourneyIdAndTboxId(journeyDto.getTboxJourneyId(), journeyDto.getTboxId());
        if(null == journey) {
            journey = new JourneyDtoAssembler().fromDto(journeyDto);
            journey.setTboxId(journeyDto.getTboxId());
            journey.setStatus(RUNNING_STATUS);
            journeyDaoService.createJourney(journey);
        }
        vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_JOURNEY);
        statusService.updateVehicleStatus(vehicleInfoDto);
    }

    @Override
    public void endJourney(JourneyDto journeyDto, VehicleInfoDto startVehicleInfoDto, VehicleInfoDto endVehicleInfoDto) {
        Journey journey = findJourneyByTboxJourneyIdAndTboxId(journeyDto.getTboxJourneyId(), journeyDto.getTboxId());
        if(null == journey) {
            journey = new JourneyDtoAssembler().fromDto(journeyDto);
            journey.setTboxId(journeyDto.getTboxId());
            journey.setStatus(END_STATUS);
            journeyDaoService.createJourney(journey);
        }
        else {
            if(null != journey.getStartTime() && null != journeyDto.getStartTime()
                    && journeyDto.getStartTime().before(journey.getStartTime())) {
                journey.setStartTime(journeyDto.getStartTime());
            }
            journey.setEndTime(journeyDto.getEndTime());
            journey.setDistance(journeyDto.getDistance());
            journey.setAvgSpeed(journeyDto.getAvgSpeed());
            journey.setFuelConsumption(journeyDto.getFuelConsumption());
            journey.setStatus(END_STATUS);
            journeyDaoService.updateJourney(journey);
        }
        startVehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_JOURNEY);
        startVehicleInfoDto.setSourceId(journey.getId());
        startVehicleInfoDto = statusService.updateVehicleStatus(startVehicleInfoDto);
        endVehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_JOURNEY);
        endVehicleInfoDto.setSourceId(journey.getId());
        endVehicleInfoDto = statusService.updateVehicleStatus(endVehicleInfoDto);
        journey.setStartVehicleInfoId(startVehicleInfoDto.getId());
        journey.setEndVehicleInfoId(endVehicleInfoDto.getId());
        journeyDaoService.updateJourney(journey);
    }

    /**
     * 根据tboxJourneyId获得Journey对象，并增加业务验证
     * @param tboxJourneyId
     * @return
     */
    private Journey findJourneyByTboxJourneyIdAndTboxId(Integer tboxJourneyId, Long tboxId) {
        if(null == tboxJourneyId) {
            throw new TboxJourneyIdNotFindException();
        }
        return journeyDaoService.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, tboxId);
    }
}
