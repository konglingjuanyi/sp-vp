/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.SvtServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDao;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import com.zxq.iov.cloud.sp.vp.service.ISvtService;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防服务 被盗追踪服务接口实现类
 */
@Service
public class SvtServiceImpl extends BaseService implements ISvtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SvtServiceImpl.class);

    @Autowired
    private IStolenAlarmDao stolenAlarmDao;
    @Autowired
    private IStatusService statusService;

    @Override
    public void alarm(Tbox tbox, StolenAlarm stolenAlarm, VehiclePos vehiclePos,
                      Long eventId) throws ServLayerException {
        stolenAlarm.setTboxId(tbox.getTboxId());
        stolenAlarm.setVehicleInfoId(statusService.logVehicleInfo(tbox,
                Constants.VEHICLE_INFO_SOURCE_SVT, eventId, vehiclePos, null, null,
                stolenAlarm.getAlarmTime(), eventId).getId());
        stolenAlarmDao.createStolenAlarm(stolenAlarm);
    }

    @Override
    public void updateTrack(Tbox tbox, List<VehicleStatus> vehicleStatuses,
                            VehiclePos vehiclePos, Long eventId) throws ServLayerException {
        statusService.logVehicleInfo(tbox, Constants.VEHICLE_INFO_SOURCE_SVT,
                eventId, vehiclePos, vehicleStatuses, null, null, eventId);
    }

}