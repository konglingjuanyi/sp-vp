package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.ISvtService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ProtectStrategySettingDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.TrackDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt.StolenAlarmDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.MsgUtil;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 被盗追踪服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-15 13:03
 * modify date 2015-7-24 10:58
 * @version 0.6, 2015-7-24
 */
@Service
@Qualifier("svtService")
public class SvtServiceImpl extends BaseService implements ISvtService {

    @Autowired
    private IStolenAlarmDaoService stolenAlarmDaoService;
    @Autowired
    private ITboxDaoService tboxDaoService;
    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;
    @Autowired
    private IEvent event;

    @Override
    public void alarm(OtaDto otaDto, List<StolenAlarmDto> stolenAlarmDtos) throws Exception {
        StolenAlarm stolenAlarm;
        StolenAlarmDtoAssembler stolenAlarmDtoAssembler = new StolenAlarmDtoAssembler();
        StepInstance stepInstance = event.findInstance(tboxDaoService.findVinById(otaDto.getTboxId()),
                otaDto.getAid()+otaDto.getMid());
        Long eventId = stepInstance.getTaskInstance().getEventInstanceId();
        for(StolenAlarmDto stolenAlarmDto : stolenAlarmDtos) {
            stolenAlarm = stolenAlarmDtoAssembler.fromDto(stolenAlarmDto);
            stolenAlarm.setTboxId(otaDto.getTboxId());
            stolenAlarm.setVehicleInfoId(statusService.updateVehicleStatus(otaDto,
                    Constants.VEHICLE_INFO_SOURCE_SVT, eventId, stolenAlarmDto.getVehiclePosDto(), null, null));
            stolenAlarmDaoService.createStolenAlarm(stolenAlarm);
        }
    }

    @Override
    public void updateTrack(OtaDto otaDto, List<TrackDto> trackDtos) throws Exception {
        StepInstance stepInstance = event.findInstance(tboxDaoService.findVinById(otaDto.getTboxId()),
                otaDto.getAid() + otaDto.getMid());
        Long eventId = stepInstance.getTaskInstance().getEventInstanceId();
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
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
            statusService.updateVehicleStatus(otaDto, Constants.VEHICLE_INFO_SOURCE_SVT,
                    eventId, trackDto.getVehiclePosDto(), vehicleStatusDtos, null);
            vehicleStatusDtos.clear();
        }
    }

    @Override
    public void requestTrackSetting(String vin, Integer trackInterval, Integer tracks) throws Exception {
        AssertRequired("vin", vin);
        // 此处没有业务，仅仅将指令发给TBOX
    }

    @Override
    public void requestSingleTrack(String vin) throws Exception {
        AssertRequired("vin", vin);
        // 此处没有业务，仅仅将指令发给TBOX
    }

    @Override
    public void requestCloseAlarm(String vin) throws Exception {
        AssertRequired("vin", vin);
        // 此处没有业务，仅仅将指令发给TBOX
    }

    @Override
    public void responseCloseAlarm(OtaDto otaDto, Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos) {
//        alarm(stolenAlarmDtos, vehicleInfoDtos);
    }

    @Override
    public void requestAuthKey(String vin, Integer keyId) throws Exception {
        AssertRequired("vin,keyId", vin, keyId);
        // 此处没有业务，仅仅将指令发给TBOX
    }

    @Override
    public void responseAuthKey(OtaDto otaDto, Boolean keyIsAccepted, Integer failureReason) {
        // 此处没有业务，仅仅将指令发给TBOX
    }

    @Override
    public void requestImmobilise(String vin, Integer immoStatus) throws Exception {
        AssertRequired("vin,immoStatus", vin, immoStatus);
        // 此处没有业务，仅仅将指令发给TBOX
    }

    @Override
    public void responseImmobilise(OtaDto otaDto, Integer immoStatus, Integer failureReason) {
        if(null != failureReason) {
            MsgUtil.pushDevice("", failureReason.toString());
        }
    }

    @Override
    public void requestUpdateProtectStrategy(String vin, Date startTime, Date endTime,
                                             List<ProtectStrategySettingDto> protectStrategySettingDtos)
            throws Exception{
        AssertRequired("vin", vin);
        // 此处没有业务，仅仅将指令发给TBOX
    }

    @Override
    public void responseUpdateProtectStrategy() {
        // 此处输入有问题，待确认
    }

    @Override
    public void requestAlarm(String vin) throws Exception {
        AssertRequired("vin", vin);
        // 此处没有业务，仅仅将指令发给TBOX
    }
}
