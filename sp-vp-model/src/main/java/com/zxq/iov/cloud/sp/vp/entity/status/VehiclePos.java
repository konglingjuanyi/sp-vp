package com.zxq.iov.cloud.sp.vp.entity.status;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 车辆位置信息类
 * @author 叶荣杰
 * create time 2015-5-13 10:14
 * @version 0.1, 2015-5-13
 */
@Entity()
@Table(name = "TB_VEHICLE_POS")
public class VehiclePos extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_vehicle_pos";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_INFO_ID", nullable = false)
    private VehicleInfo vehicleInfo;

    @Column(name = "LONGITUDE", nullable = false, precision = 11, scale = 0)
    private Integer longitude;

    @Column(name = "LATITUDE", nullable = false, precision = 11, scale = 0)
    private Integer latitude;

    @Column(name = "ALTITUDE", nullable = false, precision = 11, scale = 0)
    private Integer altitude;

    @Column(name = "HEADING", nullable = false, precision = 3, scale = 0)
    private Integer heading;

    @Column(name = "SPEED", nullable = false, precision = 3, scale = 0)
    private Integer speed;

    @Column(name = "HDOP", nullable = false, precision = 3, scale = 0)
    private Integer hdop;

    @Column(name = "SATELLITES", nullable = false, precision = 2, scale = 0)
    private Integer satellites;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "GPS_TIME", nullable = false, length = 7)
    private Date gpsTime;

    @Column(name = "GPS_STATUS", nullable = false, precision = 2, scale = 0)
    private Integer gpsStatus;

    public VehiclePos() {}

    public VehiclePos(Long id, Integer longitude, Integer latitude, Integer altitude, Integer heading, Integer speed, Integer hdop, Integer satellites, Date gpsTime, Integer gpsStatus) {
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

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
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
