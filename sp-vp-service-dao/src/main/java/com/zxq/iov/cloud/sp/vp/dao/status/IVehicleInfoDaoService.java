package com.zxq.iov.cloud.sp.vp.dao.status;

import com.zxq.iov.cloud.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

import java.util.List;

/**
 * 安防 车辆信息持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-13 10:55
 * modify date 2015-7-21 15:14
 * @version 0.3, 2015-7-21
 */
public interface IVehicleInfoDaoService extends BaseService<VehicleInfo, Long> {

	/**
	 * 创建车辆信息
	 * @param vehicleInfo	车辆信息对象
	 * @return				车辆信息对象
	 */
	VehicleInfo createVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 更新车辆信息
	 * @param vehicleInfo	车辆信息对象
	 * @return				车辆信息对象
	 */
	VehicleInfo updateVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 根据主键得到车辆信息对象
	 * @param vehicleInfoId 主键
	 * @return				车辆信息对象
	 */
	VehicleInfo findVehicleInfoById(Long vehicleInfoId);

	/**
	 * 根据车辆唯一码（VIN）得到车辆最新信息
	 * @param vin       	车辆唯一码
	 * @return         		车辆信息对象
	 */
	VehicleInfo findLatestVehicleInfo(String vin);

	/**
	 * 根据事件ID得到车辆状态快照列表
	 * @param eventId    	事件ID
	 * @return          	车辆状态快照列表
	 */
	List<VehicleInfo> listVehicleInfoByEventId(Long eventId);
	
}