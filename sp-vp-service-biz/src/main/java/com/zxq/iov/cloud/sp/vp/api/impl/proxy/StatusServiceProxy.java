package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusReqDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防 车辆状态服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-16 16:16
 * modify date 2015-7-6 17:01
 * @version 0.6, 2015-7-6
 */
@Service
@Qualifier("statusServiceProxy")
public class StatusServiceProxy extends BaseProxy implements IStatusService {

    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;
    @Autowired
    private IEvent event;

    @Override
    public void requestVehicleStatus(String vin, Integer statusType) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_STATUS, 1);
        event.start(otaDto);
        statusService.requestVehicleStatus(vin, statusType);
        sendQueue(otaDto, new VehicleStatusReqDto(statusType));
        event.end(otaDto);
    }

    @Override
    public void responseVehicleStatus(OtaDto otaDto, Date statusTime, VehiclePosDto vehiclePosDto,
                                      List<VehicleStatusDto> vehicleStatusDtos,
                                      List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        statusService.responseVehicleStatus(otaDto, statusTime, vehiclePosDto, vehicleStatusDtos,
                vehicleAlertDtos);
        event.end(otaDto);
    }

    @Override
    public void logVehicleAlert(OtaDto otaDto, List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        statusService.logVehicleAlert(otaDto, vehicleAlertDtos);
        event.end(otaDto);
    }

}
