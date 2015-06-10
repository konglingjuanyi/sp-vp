package com.zxq.iov.cloud.sp.vp.api.dto.status;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;
import java.util.List;

/**
 * 安防 车辆传输对象
 *
 * @author 叶荣杰
 * create date 2015-5-13 13:58
 * @version 0.1, 2015-5-13
 */
public class VehicleInfoDto extends OtaDto {

    private Long id;
    private Integer sourceType;
    private Date statusTime;
    private Long ownerId;
    private Long userId;
    private VehiclePosDto vehiclePosDto;
    private List<VehicleStatusDto> vehicleStatusDtos;
    private List<VehicleAlertDto> vehicleAlertDtos;

    public VehicleInfoDto() {}

    public VehicleInfoDto(Long id, Integer sourceType, Date statusTime, Long ownerId, Long userId) {
        this.id = id;
        this.sourceType = sourceType;
        this.statusTime = statusTime;
        this.ownerId = ownerId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
