package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 车辆状态服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-16 16:16
 * modify date
 * @version 0.1, 2015-6-16
 */
@Service
@Qualifier("statusServiceProxy")
public class StatusServiceProxy implements IStatusService {

    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;
    @Autowired
    private IEvent event;

    @Override
    public void requestVehicleStatus(VehicleInfoDto vehicleInfoDto, Integer statusType) {
        event.start(vehicleInfoDto);
        statusService.requestVehicleStatus(vehicleInfoDto, statusType);
        event.end(vehicleInfoDto);
    }

    @Override
    public VehicleInfoDto updateVehicleStatus(VehicleInfoDto vehicleInfoDto) {
        event.start(vehicleInfoDto);
        vehicleInfoDto = statusService.updateVehicleStatus(vehicleInfoDto);
        event.end(vehicleInfoDto, vehicleInfoDto);
        return vehicleInfoDto;
    }

    @Override
    public VehicleInfoDto getVehicleStatus(VehicleInfoDto vehicleInfoDto) {
        event.start(vehicleInfoDto);
        vehicleInfoDto = statusService.getVehicleStatus(vehicleInfoDto);
        event.end(vehicleInfoDto, vehicleInfoDto);
        return vehicleInfoDto;
    }

    @Override
    public void logVehicleAlert(VehicleInfoDto vehicleInfoDto) {
        event.start(vehicleInfoDto);
        statusService.logVehicleAlert(vehicleInfoDto);
        event.end(vehicleInfoDto);
    }
}
