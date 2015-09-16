/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-06-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.rvc;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;

import java.util.List;

/**
 * 安防服务 控制命令数据访问接口
 */
public interface IControlCommandDao extends BaseService<ControlCommand, Long> {

	/**
	 * 创建控制命令
	 *
	 * @param controlCommand 控制命令对象
	 * @return 控制命令对象
	 */
	ControlCommand createControlCommand(ControlCommand controlCommand);

	/**
	 * 更新控制命令
	 *
	 * @param controlCommand 控制命令对象
	 * @return 控制命令对象
	 */
	ControlCommand updateControlCommand(ControlCommand controlCommand);

	/**
	 * 删除控制命令
	 *
	 * @param controlCommandId 控制命令ID
	 */
	void removeControlCommand(Long controlCommandId);

	/**
	 * 根据主键得到控制命令对象
	 *
	 * @param controlCommandId 控制命令主键
	 * @return 控制命令对象
	 */
	ControlCommand findControlCommandById(Long controlCommandId);

	/**
	 * 根据事件ID得到控制命令对象
	 *
	 * @param eventId 事件ID
	 * @return 控制命令对象
	 */
	ControlCommand findControlCommandByEventId(Long eventId);

	/**
	 * 根据命令得到控制命令列表
	 *
	 * @param vin     车辆唯一码
	 * @param command 控制命令
	 * @param status  状态
	 * @return 控制命令对象列表
	 */
	List<ControlCommand> listControlCommandByVinAndCommand(String vin, String command, Integer status);

}