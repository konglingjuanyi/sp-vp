package com.zxq.iov.cloud.sp.vp.dao.status.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDao;
import com.zxq.iov.cloud.sp.vp.dao.status.repo.IVehicleInfoRepository;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防 车辆信息持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-5-13 11:32
 * modify date 2015-7-21 15:15
 * @version 0.3, 2015-7-21
 */
@Service
public class VehicleInfoDaoImpl extends BaseServiceImpl<IVehicleInfoRepository, VehicleInfo, Long> implements IVehicleInfoDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleInfoDaoImpl.class);

    @Autowired
	public VehicleInfoDaoImpl(IVehicleInfoRepository repo){
		super(repo);
	}

	@Override
	public VehicleInfo createVehicleInfo(VehicleInfo vehicleInfo) {
		if (vehicleInfo == null){
			LOGGER.error("VehicleInfo cannot be null");
		}
		vehicleInfo.setId(null);
		super.save(vehicleInfo);
		// 写入缓存
		return vehicleInfo;
	}

	@Override
	public VehicleInfo updateVehicleInfo(VehicleInfo vehicleInfo) {
		if (vehicleInfo == null){
			LOGGER.error("VehicleInfo cannot be null");
		}
		super.update(vehicleInfo);
		return vehicleInfo;
	}

	@Override
	public VehicleInfo writeVehicleInfo(VehicleInfo vehicleInfo) {
		return vehicleInfo;
	}

	@Override
	public VehicleInfo readVehicleInfo(String vin) {
		return null;
	}

	@Override
	public VehicleInfo findVehicleInfoById(Long vehicleInfoId) {
		if (vehicleInfoId == null){
			LOGGER.error("VehicleInfo ID cannot be null");
		}
		return super.findOne(vehicleInfoId);
	}

	@Override
	public VehicleInfo findLatestVehicleInfo(String vin) {
		VehicleInfo vehicleInfo = new VehicleInfo();
		// 读取缓存
		return vehicleInfo;
	}

	@Override
	public List<VehicleInfo> listVehicleInfoByEventId(Long eventId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", eventId);
		return super.findListViaBatis(paramMap);
	}
}