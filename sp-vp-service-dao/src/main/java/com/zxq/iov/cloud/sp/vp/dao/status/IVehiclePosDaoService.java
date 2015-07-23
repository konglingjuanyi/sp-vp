package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

/**
 * 安防 车辆位置信息持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-13 11:00
 * modify date 2015-5-14 13:51
 * @version 0.2, 2015-5-14
 */
public interface IVehiclePosDaoService extends BaseService<VehiclePos, Long> {

	/**
	 * 创建车辆位置信息
	 * @param vehiclePos	车辆位置信息对象
	 * @return				车辆位置信息对象
	 */
	VehiclePos createVehiclePos(VehiclePos vehiclePos);

	/**
	 * 更新车辆位置信息
	 * @param vehiclePos	车辆位置信息对象
	 * @return				车辆位置信息对象
	 */
	VehiclePos updateVehiclePos(VehiclePos vehiclePos);

	/**
	 * 根据主键得到车辆位置信息对象
	 * @param vehiclePosId 	主键
	 * @return				车辆位置信息对象
	 */
	VehiclePos findVehiclePosById(Long vehiclePosId);

	/**
	 * 根据车辆唯一码（VIN）得到车辆最新位置信息
	 * @param vin         	车辆唯一码
	 * @return           	车辆位置信息对象
	 */
	VehiclePos findLatestVehiclePos(String vin);

	/**
	 * 根据车辆信息ID得到车辆位置信息
	 * @param vehicleInfoId	车辆信息ID
	 * @return				车辆位置信息对象
	 */
	VehiclePos findVehiclePosByVehicleInfoId(Long vehicleInfoId);
	
}