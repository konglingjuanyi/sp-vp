package com.zxq.iov.cloud.sp.vp.dao.status.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDao;
import com.zxq.iov.cloud.sp.vp.dao.status.repo.IVehicleStatusRepository;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防 车辆状态信息持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-5-13 11:41
 * modify date 2015-5-14
 * @version 0.2, 2015-5-14
 */
@Service
public class VehicleStatusDaoImpl extends BaseServiceImpl<IVehicleStatusRepository, VehicleStatus, Long> implements IVehicleStatusDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleStatusDaoImpl.class);

    @Autowired
	public VehicleStatusDaoImpl(IVehicleStatusRepository repo){
		super(repo);
	}

	@Override
	public VehicleStatus createVehicleStatus(VehicleStatus vehicleStatus) {
		if (vehicleStatus == null){
			LOGGER.error("VehicleStatus cannot be null");
		}
		vehicleStatus.setId(null);
		super.save(vehicleStatus);
		// 写入缓存
		return vehicleStatus;
	}

	@Override
	public VehicleStatus updateVehicleStatus(VehicleStatus vehicleStatus) {
		if (vehicleStatus == null){
			LOGGER.error("VehicleStatus cannot be null");
		}
		super.update(vehicleStatus);
		return vehicleStatus;
	}

	@Override
	public VehicleStatus findVehicleStatusById(Long vehicleStatusId) {
		if (vehicleStatusId == null){
			LOGGER.error("VehicleStatus ID cannot be null");
		}
		return super.findOne(vehicleStatusId);
	}

	@Override
	public List<VehicleStatus> findLatestVehicleStatus(String vin, Integer type) {
		List<VehicleStatus> vehicleStatuses = new ArrayList<>();
		// 读取缓存
		return vehicleStatuses;
	}

	@Override
	public List<VehicleStatus> findVehicleStatusByVehicleInfoId(Long vehicleInfoId, Integer type) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vehicleInfoId", vehicleInfoId);
		paramMap.put("type", type);
		return super.findListViaBatis(paramMap);
	}
}