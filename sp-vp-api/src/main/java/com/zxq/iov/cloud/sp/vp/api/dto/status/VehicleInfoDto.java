package com.zxq.iov.cloud.sp.vp.api.dto.status;

import java.io.Serializable;
import java.util.List;

/**
 * 安防 车辆状态快照传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-21 13:54
 * modify date 2015-7-21 9:46
 * @version 0.1, 2015-7-21
 */
public class VehicleInfoDto implements Serializable {

    private VehiclePosDto vehiclePosDto;
    private List<VehicleStatusDto> vehicleStatusDtos;
    private List<VehicleAlertDto> vehicleAlertDtos;

    public VehicleInfoDto() {}

    public VehiclePosDto getVehiclePosDto() {
        return vehiclePosDto;
    }

    public void setVehiclePosDto(VehiclePosDto vehiclePosDto) {
        this.vehiclePosDto = vehiclePosDto;
    }

    public List<VehicleStatusDto> getVehicleStatusDtos() {
        return vehicleStatusDtos;
    }

    public void setVehicleStatusDtos(List<VehicleStatusDto> vehicleStatusDtos) {
        this.vehicleStatusDtos = vehicleStatusDtos;
    }

    public List<VehicleAlertDto> getVehicleAlertDtos() {
        return vehicleAlertDtos;
    }

    public void setVehicleAlertDtos(List<VehicleAlertDto> vehicleAlertDtos) {
        this.vehicleAlertDtos = vehicleAlertDtos;
    }
}
