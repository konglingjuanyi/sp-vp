package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.List;

/**
 * 安防 车辆状态信息持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-13 11:17
 * modify date 2015-5-14 14:33
 * @version 0.2, 2015-5-14
 */
public interface IVehicleStatusDaoService extends BaseService<VehicleStatus, Long> {

	/**
	 * 创建车辆状态信息
	 * @param vehicleStatus		车辆状态信息对象
	 * @return					车辆状态信息对象
	 */
	VehicleStatus createVehicleStatus(VehicleStatus vehicleStatus);

	/**
	 * 更新车辆状态信息
	 * @param vehicleStatus		车辆状态信息对象
	 * @return					车辆状态信息对象
	 */
	VehicleStatus updateVehicleStatus(VehicleStatus vehicleStatus);

	/**
	 * 根据主键得到车辆状态信息对象
	 * @param vehicleStatusId 	主键
	 * @return					车辆状态信息对象
	 */
	VehicleStatus findVehicleStatusById(Long vehicleStatusId);

	/**
	 * 根据车辆唯一码（VIN）得到车辆状态信息列表
	 * @param vin      			车辆唯一码
	 * @param type           	状态类型
	 * @return                	车辆状态信息列表
	 */
	List<VehicleStatus> findLatestVehicleStatus(String vin, Integer type);

	/**
	 * 根据车辆信息ID得到车辆位置信息列表
	 * @param vehicleInfoId  	车辆信息ID
	 * @param type            	状态类型
	 * @return                  车辆状态信息列表
	 */
	List<VehicleStatus> findVehicleStatusByVehicleInfoId(Long vehicleInfoId, Integer type);
}