/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-24       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.2
 *
 * com.zxq.iov.cloud.sp.vp.common.constants.ExceptionConstants
 *
 * sp - sp-vp-common
 */

package com.zxq.iov.cloud.sp.vp.common.constants;

/**
 * 安防 异常常量类
 */
public class ExceptionConstants {

	// 基础异常
	public static final String PARAMS_IS_NULL = "1"; // 参数为空

	// 事件相关异常
	public static final String HAS_CHILD = ""; // 存在子元素
	public static final String AID_NOT_MATCH = ""; // AID不匹配
	public static final String MID_NOT_MATCH = ""; // MID不匹配
	public static final String START_CODE_NOT_MATCH = "15000"; // 没有符合的启动代码
	public static final String PRE_NOT_FIND = "15001"; // 没找到前置
	public static final String CYCLE_LIMIT = "15002"; // 达到循环上限
	public static final String MORE_THAN_RETRY_COUNT = "15003"; // 超过重试上限
	public static final String EVENT_NOT_EXIST = "15004"; // 事件不存在

	public static final String USER_NOT_OWNER = "15201"; // 当前用户不是车主
	public static final String VIN_NOT_EXIST = "15202"; // VIN码不存在

	public static final String WRONG_CONTROL_CMD = "15100"; // 错误的控制命令
	public static final String WRONG_CONTROL_CMD_PARAM = "15109"; // 错误的控制命令参数
	public static final String WRONG_VEHICLE_STATUS = "15101"; // 错误的车辆状态类型
	public static final String TBOX_NOT_MATCH_VIN = "15102"; // TBOX无法匹配车辆
	public static final String PERSONAL_CONFIG_NOT_FIND = "15103"; // 没有找到指定车辆的个性化配置
	public static final String NO_PRIVILEGE_TO_VEHICLE = "15104"; // 对该车辆无权限
	public static final String CONTROL_CMD_NOT_EXIST = "15105"; // 控制命令不存在
	public static final String NO_OPEN_BCALL = "15106"; // 没有有效的bCall
	public static final String NO_OPEN_ECALL = "15107"; // 没有有效的eCall
	public static final String NO_OPEN_ICALL = "15108"; // 没有有效的iCall
}
