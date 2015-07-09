package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDaoService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防 车辆状态服务实现类
 *
 * @author 叶荣杰
 * create date 2015-5-13 16:37
 * modify date 2015-7-2 10:44
 * @version 0.8, 2015-7-2
 */
@Service
@Qualifier("statusService")
public class StatusServiceImpl implements IStatusService {

    @Autowired
    private IVehicleInfoDaoService vehicleInfoDaoService;
    @Autowired
    private IVehiclePosDaoService vehiclePosDaoService;
    @Autowired
    private ITboxDaoService tboxDaoService;
    @Autowired
    private IVehicleStatusDaoService vehicleStatusDaoService;

    @Override
    public void requestVehicleStatus(String vin, Integer statusType) {
         // 无业务操作，仅发送给TBOX
    }

    @Override
    public void responseVehicleStatus(OtaDto otaDto, Date statusTime, VehiclePosDto vehiclePosDto, List<VehicleStatusDto> vehicleStatusDtos, List<VehicleAlertDto> vehicleAlertDtos) {
        updateVehicleStatus(otaDto, Constants.VEHICLE_INFO_SOURCE_STATUS, null, statusTime,
                vehiclePosDto, vehicleStatusDtos, vehicleAlertDtos);
    }

    @Override
    public Long updateVehicleStatus(OtaDto otaDto, Integer sourceType, Long sourceId,
                                    VehiclePosDto vehiclePosDto,
                                    List<VehicleStatusDto> vehicleStatusDtos,
                                    List<VehicleAlertDto> vehicleAlertDtos) {
        return updateVehicleStatus(otaDto, sourceType, sourceId, vehiclePosDto.getGpsTime(),
                vehiclePosDto, vehicleStatusDtos, vehicleAlertDtos);
    }

    private Long updateVehicleStatus(OtaDto otaDto, Integer sourceType, Long sourceId,
                                    Date statusTime, VehiclePosDto vehiclePosDto,
                                    List<VehicleStatusDto> vehicleStatusDtos,
                                    List<VehicleAlertDto> vehicleAlertDtos) {
        VehicleInfo vehicleInfo = new VehicleInfo(otaDto.getTboxId(), tboxDaoService.findVinById(otaDto.getTboxId()),
                sourceType, sourceId);
        vehicleInfo.setOwnerId(1L); // 此处应根据TBOXID查询主数据获得车主的USERID
        if(null == vehicleInfo.getStatusTime()) {
            vehicleInfo.setStatusTime(otaDto.getEventCreateTime());
        }
        else {
            vehicleInfo.setStatusTime(statusTime);
        }
        vehicleInfoDaoService.createVehicleInfo(vehicleInfo);
        if(null != vehiclePosDto) {
            VehiclePos vehiclePos = new VehiclePosDtoAssembler().fromDto(vehiclePosDto);
            vehiclePos.setVehicleInfo(vehicleInfo);
            vehiclePosDaoService.createVehiclePos(vehiclePos);
        }
        if(null != vehicleStatusDtos) {
            VehicleStatusDtoAssembler vehicleStatusDtoAssembler = new VehicleStatusDtoAssembler();
            for(VehicleStatusDto vehicleStatusDto : vehicleStatusDtos) {
                VehicleStatus vehicleStatus = vehicleStatusDtoAssembler.fromDto(vehicleStatusDto);
                vehicleStatus.setVehicleInfo(vehicleInfo);
                vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
            }
        }
        if(null != vehicleAlertDtos) {
            VehicleAlertDtoAssembler vehicleAlertDtoAssembler = new VehicleAlertDtoAssembler();
            for(VehicleAlertDto vehicleAlertDto : vehicleAlertDtos) {
                VehicleStatus vehicleStatus = vehicleAlertDtoAssembler.fromDto(vehicleAlertDto);
                vehicleStatus.setVehicleInfo(vehicleInfo);
                vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
            }
        }
        return vehicleInfo.getId();
    }

    @Override
    public void logVehicleAlert(OtaDto otaDto, List<VehicleAlertDto> vehicleAlertDtos) {
        logVehicleAlert(otaDto, Constants.VEHICLE_INFO_SOURCE_STATUS, null, vehicleAlertDtos);
    }

    public void logVehicleAlert(OtaDto otaDto, Integer sourceType, Long sourceId, List<VehicleAlertDto> vehicleAlertDtos) {
        String vin = tboxDaoService.findVinById(otaDto.getTboxId());
        for(VehicleAlertDto vehicleAlertDto : vehicleAlertDtos) {
            VehicleInfo vehicleInfo = new VehicleInfo(otaDto.getTboxId(), vin, sourceType, sourceId);
            vehicleInfo.setOwnerId(1L); // 此处应根据TBOXID查询主数据获得车主的USERID
            vehicleInfo.setStatusTime(vehicleAlertDto.getAlertTime());
            vehicleInfoDaoService.createVehicleInfo(vehicleInfo);
            VehiclePos vehiclePos = new VehiclePosDtoAssembler().fromDto(vehicleAlertDto.getVehiclePosDto());
            vehiclePos.setVehicleInfo(vehicleInfo);
            vehiclePosDaoService.createVehiclePos(vehiclePos);
            VehicleStatus vehicleStatus = new VehicleAlertDtoAssembler().fromDto(vehicleAlertDto);
            vehicleStatus.setVehicleInfo(vehicleInfo);
            vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
            // 缓存里写入最新状态

        }
    }
}
