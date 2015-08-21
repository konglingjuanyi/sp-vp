package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
import com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDao;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import com.zxq.iov.cloud.sp.vp.service.ISvtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 被盗追踪服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-15 13:03
 * modify date 2015-8-6 9:56
 * @version 0.7, 2015-8-6
 */
@Service
public class SvtServiceImpl extends BaseService implements ISvtService {

    @Autowired
    private IStolenAlarmDao stolenAlarmDao;
    @Autowired
    private IStatusService statusService;

    @Override
    public void alarm(Long tboxId, StolenAlarm stolenAlarm, VehiclePos vehiclePos,
                      Long eventId) throws ServLayerException {
        stolenAlarm.setTboxId(tboxId);
        stolenAlarm.setVehicleInfoId(statusService.logVehicleInfo(tboxId,
                Constants.VEHICLE_INFO_SOURCE_SVT, eventId, vehiclePos, null, null,
                stolenAlarm.getAlarmTime(), eventId).getId());
        stolenAlarmDao.createStolenAlarm(stolenAlarm);
    }

    @Override
    public void updateTrack(Long tboxId, List<VehicleStatus> vehicleStatuses,
                            VehiclePos vehiclePos, Long eventId) throws ServLayerException {
        statusService.logVehicleInfo(tboxId, Constants.VEHICLE_INFO_SOURCE_SVT,
                eventId, vehiclePos, vehicleStatuses, null, null, eventId);
    }

}