package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDao;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.service.IVehicleInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/12/11.
 */
@Service
public class VehicleInfoServiceImpl extends BaseService implements IVehicleInfoService {
    @Autowired
    private IVehicleInfoDao vehicleInfoDao;
    @Override
    public List<VehicleInfo> listVehicleInfoByEventId(long eventId) {
        return vehicleInfoDao.listVehicleInfoByEventId(eventId);
    }
}
