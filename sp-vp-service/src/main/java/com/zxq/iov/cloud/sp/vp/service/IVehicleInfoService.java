package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/12/11.
 */
public interface IVehicleInfoService {
    List<VehicleInfo> listVehicleInfoByEventId(long eventId);
}
