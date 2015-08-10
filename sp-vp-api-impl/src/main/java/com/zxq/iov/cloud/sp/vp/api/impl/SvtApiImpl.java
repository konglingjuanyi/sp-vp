package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.ISvtApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.*;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt.StolenAlarmDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.ISvtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 被盗追踪服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-15 13:03
 * modify date 2015-8-7 15:06
 * @version 0.8, 2015-8-7
 */
@Service
public class SvtApiImpl extends BaseApi implements ISvtApi {

    @Autowired
    private ISvtService svtService;
    @Autowired
    private IEventService eventService;

    @Override
    public void alarm(OtaDto otaDto, List<StolenAlarmDto> stolenAlarmDtos) throws Exception {
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        StolenAlarmDtoAssembler stolenAlarmDtoAssembler = new StolenAlarmDtoAssembler();
        VehiclePosDtoAssembler posDtoAssembler = new VehiclePosDtoAssembler();
        for(StolenAlarmDto stolenAlarmDto : stolenAlarmDtos) {
            svtService.alarm(otaDto.getTboxId(), stolenAlarmDtoAssembler.fromDto(stolenAlarmDto),
                    posDtoAssembler.fromDto(stolenAlarmDto.getVehiclePosDto()), eventId);
        }
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void updateTrack(OtaDto otaDto, List<TrackDto> trackDtos) throws Exception {
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        VehicleStatusDtoAssembler statusDtoAssembler = new VehicleStatusDtoAssembler();
        VehiclePosDtoAssembler posDtoAssembler = new VehiclePosDtoAssembler();
        for(TrackDto trackDto : trackDtos) {
            vehicleStatusDtos.add(new VehicleStatusDto("gnssSpeed", trackDto.getGnssSpeed()));
            vehicleStatusDtos.add(new VehicleStatusDto("gsmAntConnected", (trackDto.isGsmAntConnected())?1:0));
            vehicleStatusDtos.add(new VehicleStatusDto("gnssAntConnected", (trackDto.isGnssAntConnected())?1:0));
            vehicleStatusDtos.add(new VehicleStatusDto("vehicleBatteryConnected", (trackDto.isVehicleBatteryConnected())?1:0));
            vehicleStatusDtos.add(new VehicleStatusDto("intBattV", trackDto.getIntBattV()));
            vehicleStatusDtos.add(new VehicleStatusDto("vehicleAlarmStatus", trackDto.getVehicleAlarmStatus()));
            vehicleStatusDtos.add(new VehicleStatusDto("engineStatus", trackDto.getEngineStatus()));
            vehicleStatusDtos.add(new VehicleStatusDto("powerMode", trackDto.getPowerMode()));
            vehicleStatusDtos.add(new VehicleStatusDto("lastKeySeen", trackDto.getLastKeySeen()));
            vehicleStatusDtos.add(new VehicleStatusDto("fuelLevelPrc", trackDto.getFuelLevelPrc()));
            vehicleStatusDtos.add(new VehicleStatusDto("fuelRange", trackDto.getFuelRange()));
            vehicleStatusDtos.add(new VehicleStatusDto("canBusActive", (trackDto.isCanBusActive())?1:0));
            vehicleStatusDtos.add(new VehicleStatusDto("lastCanBusActiveityTime", (int)(trackDto.getLastCanBusActivityTime().getTime()/1000)));
            vehicleStatusDtos.add(new VehicleStatusDto("ttnTrackPoint", trackDto.getTtnTrackPoint()));
            svtService.updateTrack(otaDto.getTboxId(), statusDtoAssembler.fromDtoList(vehicleStatusDtos),
                    posDtoAssembler.fromDto(trackDto.getVehiclePosDto()), eventId);
            vehicleStatusDtos.clear();
        }
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void requestTrackSetting(String vin, Integer trackInterval, Integer tracks) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_SVT, 3);
        eventService.start(vin, Constants.AID_SVT + "3");
        sendQueue(otaDto, new TrackSettingReqDto(trackInterval, tracks));
        eventService.end(vin, Constants.AID_SVT + "3");
    }

    @Override
    public void requestSingleTrack(String vin) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_SVT, 4);
        eventService.start(vin, Constants.AID_SVT + "4");
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_SVT + "4");
    }

    @Override
    public void requestCloseAlarm(String vin) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_SVT, 5);
        eventService.start(vin, Constants.AID_SVT + "5");
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_SVT + "5");
    }

    @Override
    public void responseCloseAlarm(OtaDto otaDto, Boolean allAlarmClosed,
                                   List<StolenAlarmDto> stolenAlarmDtos) throws Exception {
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        if(allAlarmClosed) {
            eventService.end(getVin(otaDto), getCode(otaDto));
        }
        else {
            eventService.error(getVin(otaDto), getCode(otaDto), 1, eventId);
        }
    }

    @Override
    public void requestAuthKey(String vin, Integer keyId) throws Exception {
        AssertRequired("vin,keyId", vin, keyId);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_SVT, 7);
        eventService.start(vin, Constants.AID_SVT + "7");
        sendQueue(otaDto, new AuthKeyReqDto(keyId));
        eventService.end(vin, Constants.AID_SVT + "7");
    }

    @Override
    public void responseAuthKey(OtaDto otaDto, Boolean keyIsAccepted,
                                Integer failureReason) throws Exception {
        eventService.start(getVin(otaDto), getCode(otaDto));
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void requestImmobilise(String vin, Integer immoStatus) throws Exception {
        AssertRequired("vin,immoStatus", vin, immoStatus);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_SVT, 9);
        eventService.start(vin, Constants.AID_SVT + "9");
        sendQueue(otaDto, new ImmobiliseReqDto(immoStatus));
        eventService.end(vin, Constants.AID_SVT + "9");
    }

    @Override
    public void responseImmobilise(OtaDto otaDto, Integer immoStatus,
                                   Integer failureReason) throws Exception {
        eventService.start(getVin(otaDto), getCode(otaDto));
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void requestUpdateProtectStrategy(String vin, Date startTime, Date endTime,
                                             List<ProtectStrategySettingDto> protectStrategySettingDtos)
            throws Exception{
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_SVT, 11);
        eventService.start(vin, Constants.AID_SVT + "11");
        sendQueue(otaDto, new UpdateProtectStrategyReqDto(startTime, endTime, protectStrategySettingDtos));
        eventService.end(vin, Constants.AID_SVT + "11");
    }

    @Override
    public void responseUpdateProtectStrategy() {
        // 此处输入有问题，待确认
    }

    @Override
    public void requestAlarm(String vin) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_SVT, 13);
        eventService.start(vin, Constants.AID_SVT + "13");
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_SVT + "13");
    }
}