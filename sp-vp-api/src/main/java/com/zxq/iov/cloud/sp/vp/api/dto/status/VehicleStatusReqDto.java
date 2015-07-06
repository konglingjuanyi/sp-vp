package com.zxq.iov.cloud.sp.vp.api.dto.status;

/**
 * 安防 车辆状态请求传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 17:00
 * modify date
 * @version 0.1, 2015-7-6
 */
public class VehicleStatusReqDto {

    private Integer statusType;

    public VehicleStatusReqDto() {}

    public VehicleStatusReqDto(Integer statusType) {
        this.statusType = statusType;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public void setStatusType(Integer statusType) {
        this.statusType = statusType;
    }
}
