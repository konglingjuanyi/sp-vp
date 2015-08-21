package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDao;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDao;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDao;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 车辆状态服务实现类
 *
 * @author 叶荣杰
 * create date 2015-5-13 16:37
 * modify date 2015-8-18 14:43
 * @version 0.13, 2015-8-18
 */
@Service
public class StatusServiceImpl extends BaseService implements IStatusService {

    @Autowired
    private IVehicleInfoDao vehicleInfoDao;
    @Autowired
    private IVehiclePosDao vehiclePosDao;
    @Autowired
    private IVehicleStatusDao vehicleStatusDao;

    @Override
    public VehicleInfo getVehicleInfo(String vin, Long eventId) throws ServLayerException {
        AssertRequired("vin", vin);
        VehicleInfo vehicleInfo = null;
        if(null != eventId) {
            List<VehicleInfo> vehicleInfos = vehicleInfoDao.listVehicleInfoByEventId(eventId);
            if(vehicleInfos.size() > 0) {
                vehicleInfo = vehicleInfos.get(0);
            }
            vehicleInfo.setVehiclePos(vehiclePosDao.findVehiclePosByVehicleInfoId(vehicleInfo.getId()));
            vehicleInfo.setVehicleStatuses(vehicleStatusDao.findVehicleStatusByVehicleInfoId(
                    vehicleInfo.getId(), Constants.VEHICLE_STATUS_BASIC));
            vehicleInfo.setVehicleAlerts(vehicleStatusDao.findVehicleStatusByVehicleInfoId(
                    vehicleInfo.getId(), Constants.VEHICLE_STATUS_ALERT));
        }
        else {
            vehicleInfo = vehicleInfoDao.readVehicleInfo(vin);
        }
        return vehicleInfo;
    }

    @Override
    public VehicleInfo logVehicleInfo(Long tboxId, Integer sourceType, Long sourceId,
                                    VehiclePos vehiclePos, List<VehicleStatus> vehicleStatuses,
                                    List<VehicleStatus> vehicleAlerts, Date statusTime, Long eventId)
            throws ServLayerException {
        AssertRequired("tboxId", tboxId);
        VehicleInfo vehicleInfo = new VehicleInfo(tboxId, findVinById(tboxId), sourceType, sourceId);
        vehicleInfo.setOwnerId(findUserIdById(tboxId));
        if(null != eventId) {
            vehicleInfo.setEventId(eventId);
        }
        vehicleInfo.setStatusTime((null!=statusTime)?statusTime:new Date());
        vehicleInfoDao.createVehicleInfo(vehicleInfo);
        if(null != vehiclePos) {
            vehiclePos.setVehicleInfo(vehicleInfo);
            vehiclePosDao.createVehiclePos(vehiclePos);
        }
        if(null != vehicleStatuses) {
            for(VehicleStatus vehicleStatus : vehicleStatuses) {
                vehicleStatus.setVehicleInfo(vehicleInfo);
                vehicleStatusDao.createVehicleStatus(vehicleStatus);
            }
        }
        if(null != vehicleAlerts) {
            for(VehicleStatus vehicleStatus : vehicleAlerts) {
                vehicleStatus.setVehicleInfo(vehicleInfo);
                vehicleStatusDao.createVehicleStatus(vehicleStatus);
            }
        }
        return vehicleInfoDao.writeVehicleInfo(vehicleInfo);
    }

    @Override
    public VehicleInfo logVehicleAlert(Long tboxId, Date alertTime, VehiclePos vehiclePos,
                                VehicleStatus vehicleAlert) throws ServLayerException {
        AssertRequired("tboxId,alertTime,vehicleAlert", tboxId, alertTime, vehicleAlert);
        List<VehicleStatus> vehicleAlerts = new ArrayList<>();
        vehicleAlerts.add(vehicleAlert);
        return logVehicleInfo(tboxId, null, null, vehiclePos, null,
                vehicleAlerts, alertTime, null);
    }
}
