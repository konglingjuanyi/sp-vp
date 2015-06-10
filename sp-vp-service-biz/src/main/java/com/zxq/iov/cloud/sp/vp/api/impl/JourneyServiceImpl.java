package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IJourneyService;
import com.zxq.iov.cloud.sp.vp.api.dto.journey.JourneyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.exception.TboxJourneyIdNotFindException;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.journey.JourneyDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleInfoDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDaoService;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 行程服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-9 14:19
 * modify date 2015-6-10 15:50
 * @version 0.2, 2015-6-10
 */
@Service
@Qualifier("journeyService")
public class JourneyServiceImpl implements IJourneyService {

    @Autowired
    private IJourneyDaoService journeyDaoService;
    @Autowired
    private IVehicleInfoDaoService vehicleInfoDaoService;
    @Autowired
    private IVehiclePosDaoService vehiclePosDaoService;
    @Autowired
    private IVehicleStatusDaoService vehicleStatusDaoService;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public void startJourney(JourneyDto journeyDto) {
        Journey journey = findJourneyByTboxJourneyId(journeyDto.getTboxJourneyId());
        if(null == journey) {
            journey = new JourneyDtoAssembler().fromDto(journeyDto);
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
        Journey journey = findJourneyByTboxJourneyId(journeyDto.getTboxJourneyId());
        if(null == journey) {
            journey = new JourneyDtoAssembler().fromDto(journeyDto);
            journey.setStatus(RUNNING_STATUS);
            journeyDaoService.createJourney(journey);
        }
        VehicleInfo vehicleInfo = new VehicleInfoDtoAssembler().fromDto(vehicleInfoDto);
        vehicleInfo.setVin(journeyDto.getVin());
        vehicleInfo.setSourceType(Constants.VEHICLE_INFO_SOURCE_JOURNEY);
        vehicleInfo.setSourceId(journey.getId());
        vehicleInfo.setOwnerId(journeyDto.getOwnerId());
        vehicleInfoDaoService.createVehicleInfo(vehicleInfo);
        VehiclePos vehiclePos = new VehiclePosDtoAssembler().fromDto(vehicleInfoDto.getVehiclePosDto());
        vehiclePos.setVehicleInfo(vehicleInfo);
        vehiclePosDaoService.createVehiclePos(vehiclePos);
        VehicleStatusDtoAssembler vehicleStatusDtoAssembler = new VehicleStatusDtoAssembler();
        if(null != vehicleInfoDto.getVehicleStatusDtos()) {
            for(VehicleStatusDto vehicleStatusDto : vehicleInfoDto.getVehicleStatusDtos()) {
                VehicleStatus vehicleStatus = vehicleStatusDtoAssembler.fromDto(vehicleStatusDto);
                vehicleStatus.setVehicleInfo(vehicleInfo);
                vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
            }
        }
    }

    @Override
    public void endJourney(JourneyDto journeyDto, VehicleInfoDto startVehicleInfoDto, VehicleInfoDto endVehicleInfoDto) {
        Journey journey = findJourneyByTboxJourneyId(journeyDto.getTboxJourneyId());
        if(null == journey) {
            journey = new JourneyDtoAssembler().fromDto(journeyDto);
            journey.setStatus(END_STATUS);
            journeyDaoService.createJourney(journey);
        }
        else {
            journey.setStartTime(journeyDto.getStartTime());
            journey.setEndTime(journeyDto.getEndTime());
            journey.setDistance(journeyDto.getDistance());
            journey.setAvgSpeed(journeyDto.getAvgSpeed());
            journey.setFuelConsumption(journeyDto.getFuelConsumption());
            journey.setStatus(END_STATUS);
            journeyDaoService.updateJourney(journey);
        }
        VehicleInfoDtoAssembler vehicleInfoDtoAssembler = new VehicleInfoDtoAssembler();
        VehicleInfo startVehicleInfo = vehicleInfoDtoAssembler.fromDto(startVehicleInfoDto);
        startVehicleInfo.setVin(journeyDto.getVin());
        startVehicleInfo.setSourceType(Constants.VEHICLE_INFO_SOURCE_JOURNEY);
        startVehicleInfo.setSourceId(journey.getId());
        startVehicleInfo.setOwnerId(journeyDto.getOwnerId());
        vehicleInfoDaoService.createVehicleInfo(startVehicleInfo);
        VehiclePosDtoAssembler vehiclePosDtoAssembler = new VehiclePosDtoAssembler();
        VehiclePos startVehiclePos = vehiclePosDtoAssembler.fromDto(startVehicleInfoDto.getVehiclePosDto());
        startVehiclePos.setVehicleInfo(startVehicleInfo);
        vehiclePosDaoService.createVehiclePos(startVehiclePos);
        VehicleInfo endVehicleInfo = vehicleInfoDtoAssembler.fromDto(endVehicleInfoDto);
        endVehicleInfo.setVin(journeyDto.getVin());
        endVehicleInfo.setSourceType(Constants.VEHICLE_INFO_SOURCE_JOURNEY);
        endVehicleInfo.setSourceId(journey.getId());
        endVehicleInfo.setOwnerId(journeyDto.getOwnerId());
        vehicleInfoDaoService.createVehicleInfo(endVehicleInfo);
        VehiclePos endVehiclePos = vehiclePosDtoAssembler.fromDto(endVehicleInfoDto.getVehiclePosDto());
        endVehiclePos.setVehicleInfo(endVehicleInfo);
        vehiclePosDaoService.createVehiclePos(endVehiclePos);
        VehicleStatusDtoAssembler vehicleStatusDtoAssembler = new VehicleStatusDtoAssembler();
        if(null != endVehicleInfoDto.getVehicleStatusDtos()) {
            for(VehicleStatusDto vehicleStatusDto : endVehicleInfoDto.getVehicleStatusDtos()) {
                VehicleStatus vehicleStatus = vehicleStatusDtoAssembler.fromDto(vehicleStatusDto);
                vehicleStatus.setVehicleInfo(endVehicleInfo);
                vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
            }
        }
        journey.setStartVehicleInfoId(startVehicleInfo.getId());
        journey.setEndVehicleInfoId(endVehicleInfo.getId());
        journeyDaoService.updateJourney(journey);
    }

    /**
     * 根据tboxJourneyId获得Journey对象，并增加业务验证
     * @param tboxJourneyId
     * @return
     */
    private Journey findJourneyByTboxJourneyId(Integer tboxJourneyId) {
        if(null == tboxJourneyId) {
            throw new TboxJourneyIdNotFindException();
        }
        return journeyDaoService.findJourneyByTboxJourneyId(tboxJourneyId);
    }
}
