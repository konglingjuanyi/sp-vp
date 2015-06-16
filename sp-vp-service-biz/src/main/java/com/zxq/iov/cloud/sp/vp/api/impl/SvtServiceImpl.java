package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.ISvtService;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ImmoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.KeyAuthDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt.StolenAlarmDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 被盗追踪服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-15 13:03
 * modify date 2015-6-16 11:08
 * @version 0.2, 2015-6-16
 */
@Service
@Qualifier("svtService")
public class SvtServiceImpl implements ISvtService {

    @Autowired
    private IStolenAlarmDaoService stolenAlarmDaoService;
    @Autowired
    private IStatusService statusService;
    @Autowired
    private IEvent event;

    @Override
    public void alarm(List<StolenAlarmDto> stolenAlarmDtos, List<VehicleInfoDto> vehicleInfoDtos) {
        StolenAlarm stolenAlarm;
        VehicleInfoDto vehicleInfoDto;
        StolenAlarmDtoAssembler stolenAlarmDtoAssembler = new StolenAlarmDtoAssembler();
        StolenAlarmDto stolenAlarmDto = stolenAlarmDtos.get(0);
        StepInstance stepInstance = null;
        if(null != stolenAlarmDto.getAid()) {
            stepInstance = event.findInstance(stolenAlarmDto.getTboxId().toString(),
                    stolenAlarmDto.getAid()+stolenAlarmDto.getMid());
        }
        for(int i=0; i<stolenAlarmDtos.size(); i++) {
            stolenAlarm = stolenAlarmDtoAssembler.fromDto(stolenAlarmDtos.get(i));
            vehicleInfoDto = vehicleInfoDtos.get(i);
            vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_SVT);
            if(null != stepInstance) {
                vehicleInfoDto.setSourceId(stepInstance.getTaskInstance().getEventInstance().getId());
            }
            stolenAlarm.setVehicleInfoId(statusService.updateVehicleStatus(vehicleInfoDtos.get(i)).getId());
            stolenAlarmDaoService.createStolenAlarm(stolenAlarm);
        }
    }

    @Override
    public void updateTrack(VehicleInfoDto vehicleInfoDto) {
        vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_SVT);
        StepInstance stepInstance = null;
        if(null != vehicleInfoDto.getAid()) {
            stepInstance = event.findInstance(vehicleInfoDto.getTboxId().toString(),
                    vehicleInfoDto.getAid()+vehicleInfoDto.getMid());
            if(null != stepInstance) {
                vehicleInfoDto.setSourceId(stepInstance.getTaskInstance().getEventInstance().getId());
            }
        }
        statusService.updateVehicleStatus(vehicleInfoDto);
    }

    @Override
    public void requestTrackSetting(Long tboxId, Integer trackInterval, Integer tracks) {
        // 此处发送给queue，不记录进数据库
    }

    @Override
    public void requestSingleTrack(Long tboxId) {
        // 此处发送给queue，不记录进数据库
    }

    @Override
    public void requestCloseAlarm(Long tboxId) {
        // 此处发送给queue，不记录进数据库
    }

    @Override
    public void responseCloseAlarm(Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos,
                                   List<VehicleInfoDto> vehicleInfoDtos) {
        alarm(stolenAlarmDtos, vehicleInfoDtos);
    }

    @Override
    public void requestAuthKey(Long tboxId, Long keyId) {
        // 此处发送给queue，到底要不要进数据库不确定，用户有没有必要在云端查询绑定了多少KEY？
    }

    @Override
    public void responseAuthKey(KeyAuthDto keyAuthDto) {
        // 如果不在数据库记录则直接将反馈信息推送掉
    }

    @Override
    public void requestImmobilise(Long tboxId, Integer requestStatus) {
        // 此处发送给queue，不记录进数据库
    }

    @Override
    public void responseImmobilise(ImmoDto immoDto) {
        // 如果不在数据库记录则直接将反馈信息推送掉
    }

    @Override
    public void requestUpdateProtectStrategy(Long tboxId) {
        // 此处发送给queue，不记录进数据库
    }

    @Override
    public void responseUpdateProtectStrategy() {
        // 如果不在数据库记录则直接将反馈信息推送掉
    }

    @Override
    public void requestAlarm(Long tboxId) {
        // 此处发送给queue，不记录进数据库
    }
}
