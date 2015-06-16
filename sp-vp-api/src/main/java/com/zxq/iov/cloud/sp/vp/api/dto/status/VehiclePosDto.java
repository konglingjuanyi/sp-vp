package com.zxq.iov.cloud.sp.vp.api.dto.status;

import java.util.Date;

/**
 * 安防 车辆位置传输对象
 *
 * @author 叶荣杰
 * create date 2015-5-13 14:22
 * modify date 2015-5-14 17:11
 * @version 0.2, 2015-5-14
 */
public class VehiclePosDto {

    private Long id;
    private Integer latitude;
    private Integer longitude;
    private Integer altitude;
    private Integer heading;
    private Integer speed;
    private Integer hdop;
    private Integer satellites;
    private Date gpsTime;
    private Integer gpsStatus;

    public VehiclePosDto() {}

    public VehiclePosDto(Long id, Integer longitude, Integer latitude, Integer altitude, Integer heading, Integer speed, Integer hdop, Integer satellites, Date gpsTime, Integer gpsStatus) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.heading = heading;
        this.speed = speed;
        this.hdop = hdop;
        this.satellites = satellites;
        this.gpsTime = gpsTime;
        this.gpsStatus = gpsStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getHeading() {
        return heading;
    }

    public void setHeading(Integer heading) {
        this.heading = heading;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getHdop() {
        return hdop;
    }

    public void setHdop(Integer hdop) {
        this.hdop = hdop;
    }

    public Integer getSatellites() {
        return satellites;
    }

    public void setSatellites(Integer satellites) {
        this.satellites = satellites;
    }

    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    public Integer getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(Integer gpsStatus) {
        this.gpsStatus = gpsStatus;
    }
}