package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleInfoDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDaoService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 车辆状态服务实现类
 *
 * @author 叶荣杰
 * create date 2015-5-13 16:37
 * modify date 2015-6-15 17:04
 * @version 0.4, 2015-6-15
 */
@Service
@Qualifier("statusService")
public class StatusServiceImpl implements IStatusService {

    @Autowired
    private IVehicleInfoDaoService vehicleInfoDaoService;
    @Autowired
    private IVehiclePosDaoService vehiclePosDaoService;
    @Autowired
    private IVehicleStatusDaoService vehicleStatusDaoService;

    @Override
    public void requestVehicleStatus(VehicleInfoDto vehicleInfoDto, Integer statusType) {
         // 通知TAP对TBOX发送OTA_RVMVehicleStatusReq
    }

    @Override
    public VehicleInfoDto updateVehicleStatus(VehicleInfoDto vehicleInfoDto) {
        VehicleInfoDtoAssembler vehicleInfoDtoAssembler = new VehicleInfoDtoAssembler();
        VehicleInfo vehicleInfo = vehicleInfoDtoAssembler.fromDto(vehicleInfoDto);
        vehicleInfo.setVin("001"); // 此处应根据TBOXID查询主数据获得车辆的VIN
        vehicleInfo.setOwnerId(1L); // 此处应根据TBOXID查询主数据获得车主的USERID
        if(null == vehicleInfo.getStatusTime()) {
            vehicleInfo.setStatusTime(vehicleInfoDto.getEventCreateTime());
        }
        vehicleInfoDaoService.createVehicleInfo(vehicleInfo);
        if(null != vehicleInfoDto.getVehiclePosDto()) {
            VehiclePos vehiclePos = new VehiclePosDtoAssembler().fromDto(vehicleInfoDto.getVehiclePosDto());
            vehiclePos.setVehicleInfo(vehicleInfo);
            vehiclePosDaoService.createVehiclePos(vehiclePos);
        }
        if(null != vehicleInfoDto.getVehicleStatusDtos()) {
            VehicleStatusDtoAssembler vehicleStatusDtoAssembler = new VehicleStatusDtoAssembler();
            for(VehicleStatusDto vehicleStatusDto : vehicleInfoDto.getVehicleStatusDtos()) {
                VehicleStatus vehicleStatus = vehicleStatusDtoAssembler.fromDto(vehicleStatusDto);
                vehicleStatus.setVehicleInfo(vehicleInfo);
                vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
            }
        }
        if(null != vehicleInfoDto.getVehicleAlertDtos()) {
            VehicleAlertDtoAssembler vehicleAlertDtoAssembler = new VehicleAlertDtoAssembler();
            for(VehicleAlertDto vehicleAlertDto : vehicleInfoDto.getVehicleAlertDtos()) {
                VehicleStatus vehicleStatus = vehicleAlertDtoAssembler.fromDto(vehicleAlertDto);
                vehicleStatus.setVehicleInfo(vehicleInfo);
                vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
            }
        }
        return vehicleInfoDtoAssembler.toDto(vehicleInfo);
    }

    @Override
    public VehicleInfoDto getVehicleStatus(VehicleInfoDto vehicleInfoDto) {
        VehicleInfo vehicleInfo = null;
        VehiclePos vehiclePos = null;
        List<VehicleStatus> vehicleStatuses = null;
        List<VehicleStatus> vehicleAlerts = null;
        if(null != vehicleInfoDto.getId()) {
            vehicleInfo = vehicleInfoDaoService.findVehicleInfoById(vehicleInfoDto.getId());
            vehiclePos = vehiclePosDaoService.findVehiclePosByVehicleInfoId(vehicleInfoDto.getId());
            vehicleStatuses = vehicleStatusDaoService.findVehicleStatusByVehicleInfoId(vehicleInfoDto.getId(), 1);
            vehicleAlerts = vehicleStatusDaoService.findVehicleStatusByVehicleInfoId(vehicleInfoDto.getId(), 2);
        }
        else {
            vehicleInfo = vehicleInfoDaoService.findLatestVehicleInfo(vehicleInfoDto.getVin());
            vehiclePos = vehiclePosDaoService.findLatestVehiclePos(vehicleInfoDto.getVin());
            vehicleStatuses = vehicleStatusDaoService.findLatestVehicleStatus(vehicleInfoDto.getVin(), 1);
            vehicleAlerts = vehicleStatusDaoService.findLatestVehicleStatus(vehicleInfoDto.getVin(), 2);
        }
        vehicleInfoDto = new VehicleInfoDtoAssembler().toDto(vehicleInfo);
        vehicleInfoDto.setVehiclePosDto(new VehiclePosDtoAssembler().toDto(vehiclePos));
        vehicleInfoDto.setVehicleStatusDtos(new VehicleStatusDtoAssembler().toDtoList(vehicleStatuses));
        vehicleInfoDto.setVehicleAlertDtos(new VehicleAlertDtoAssembler().toDtoList(vehicleAlerts));
        return vehicleInfoDto;
    }

    @Override
    public void logVehicleAlert(VehicleInfoDto vehicleInfoDto) {
        VehicleInfo vehicleInfo = new VehicleInfoDtoAssembler().fromDto(vehicleInfoDto);
        vehicleInfo.setVin("001"); // 此处应根据TBOXID查询主数据获得车辆的VIN
        vehicleInfo.setOwnerId(1L); // 此处应根据TBOXID查询主数据获得车主的USERID
        vehicleInfoDaoService.createVehicleInfo(vehicleInfo);
        VehiclePos vehiclePos = new VehiclePosDtoAssembler().fromDto(vehicleInfoDto.getVehiclePosDto());
        vehiclePos.setVehicleInfo(vehicleInfo);
        vehiclePosDaoService.createVehiclePos(vehiclePos);
        VehicleAlertDtoAssembler vehicleAlertDtoAssembler = new VehicleAlertDtoAssembler();
        for(VehicleAlertDto vehicleAlertDto : vehicleInfoDto.getVehicleAlertDtos()) {
            VehicleStatus vehicleStatus = vehicleAlertDtoAssembler.fromDto(vehicleAlertDto);
            vehicleStatus.setVehicleInfo(vehicleInfo);
            vehicleStatusDaoService.createVehicleStatus(vehicleStatus);
        }
        // 调用消息接口通知用户
    }

}
