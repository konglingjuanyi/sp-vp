package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

import java.util.List;

/**
 * 安防 车辆信息持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-13 10:55
 * modify date 2015-8-4 11:19
 * @version 0.4, 2015-8-4
 */
public interface IVehicleInfoDao extends BaseService<VehicleInfo, Long> {

	/**
	 * 创建车辆信息快照
	 * @param vehicleInfo	车辆信息快照对象
	 * @return				车辆信息快照对象
	 */
	VehicleInfo createVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 更新车辆信息快照
	 * @param vehicleInfo	车辆信息快照对象
	 * @return				车辆信息快照对象
	 */
	VehicleInfo updateVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 写入车辆最新信息缓存
	 * @param vehicleInfo 	车辆信息快照对象
	 * @return           	车辆信息快照对象
	 */
	VehicleInfo writeVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 读取车辆最新信息缓存
	 * @param vin    		车辆唯一码
	 * @return             	车辆信息快照对象
	 */
	VehicleInfo readVehicleInfo(String vin);

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