/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-12       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.2
 *
 * com.zxq.iov.cloud.sp.vp.common.constants.Constants
 *
 * sp - sp-vp-common
 */

package com.zxq.iov.cloud.sp.vp.common.constants;

import java.util.Map;
import java.util.TreeMap;

/**
 * 安防 常量类
 */
public class Constants {

	public static final String QUEUE_ROUTING_KEY = "AppTBOXServiceQueue.AV.1.0";

	public static final Integer REMOTE_KEY_TYPE_RESCUE = 0;
	public static final Integer REMOTE_KEY_TYPE_TEMPORARY = 1;

	public static final Integer CALL_TYPE_BCALL = 1;
	public static final Integer CALL_TYPE_ECALL = 2;
	public static final Integer CALL_TYPE_ICALL = 3;

	public static final Integer VEHICLE_STATUS_BASIC = 1;
	public static final Integer VEHICLE_STATUS_ALERT = 2;

	public static final Integer VEHICLE_INFO_SOURCE_JOURNEY = 1;
	public static final Integer VEHICLE_INFO_SOURCE_BCALL = 2;
	public static final Integer VEHICLE_INFO_SOURCE_ECALL = 3;
	public static final Integer VEHICLE_INFO_SOURCE_ICALL = 4;
	public static final Integer VEHICLE_INFO_SOURCE_SVT = 5;
	public static final Integer VEHICLE_INFO_SOURCE_RVC = 6;
	public static final Integer VEHICLE_INFO_SOURCE_STATUS = 7;

	public static final String RVC_CMD_FIND_MY_CAR = "find_my_car";
	public static final String RVC_CMD_VEHICLE_LOCK = "vehicle_lock";
	public static final String RVC_CMD_VEHICLE_UNLOCK = "vehicle_unlock";
	public static final String RVC_CMD_WINDOW_CONTROL = "window_control";
	public static final String RVC_CMD_KEY_MANAGEMENT = "key_management";
	public static final String RVC_CMD_HEATED_SEAT_CONTROL = "heated_seat_control";
	public static final String RVC_CMD_CLIMATE_CONTROL = "climate_control";
	public static final String RVC_CMD_ENGINE_CONTROL = "engine_control";
	public static final Map<String, String> RVC_CMD = new TreeMap<>();
	static {
		RVC_CMD.put(RVC_CMD_FIND_MY_CAR, "定位车辆");
		RVC_CMD.put(RVC_CMD_VEHICLE_LOCK, "锁车门");
		RVC_CMD.put(RVC_CMD_VEHICLE_UNLOCK, "解锁车门");
		RVC_CMD.put(RVC_CMD_WINDOW_CONTROL, "控制车窗");
		RVC_CMD.put(RVC_CMD_KEY_MANAGEMENT, "管理钥匙");
		RVC_CMD.put(RVC_CMD_HEATED_SEAT_CONTROL, "加热座椅");
		RVC_CMD.put(RVC_CMD_CLIMATE_CONTROL, "控制空调");
		RVC_CMD.put(RVC_CMD_ENGINE_CONTROL, "控制引擎");
	}
	public static final Map<String, String> RVC_CMD_CODE = new TreeMap<>();
	static {
		RVC_CMD_CODE.put(RVC_CMD_FIND_MY_CAR, "00");
		RVC_CMD_CODE.put(RVC_CMD_VEHICLE_LOCK, "01");
		RVC_CMD_CODE.put(RVC_CMD_VEHICLE_UNLOCK, "02");
		RVC_CMD_CODE.put(RVC_CMD_WINDOW_CONTROL, "03");
		RVC_CMD_CODE.put(RVC_CMD_KEY_MANAGEMENT, "04");
		RVC_CMD_CODE.put(RVC_CMD_HEATED_SEAT_CONTROL, "05");
		RVC_CMD_CODE.put(RVC_CMD_CLIMATE_CONTROL, "06");
		RVC_CMD_CODE.put(RVC_CMD_ENGINE_CONTROL, "11");
	}

	public static final String MSG_TYPE_RVC = "control";

	public static final String RVC_CMD_PARAM_FMC_LIGHTS = "light";
	public static final String RVC_CMD_PARAM_FMC_HORN = "horn";
	public static final String RVC_CMD_PARAM_FMC_LENGTH = "horn_length";
	public static final String RVC_CMD_PARAM_UNLOCK_SILENT_FLAG = "silent_flag";
	public static final String RVC_CMD_PARAM_UNLOCK_SELENT_TIMER = "silent_timer";
	public static final String RVC_CMD_PARAM_UNLOCK_PM_OVERRIDE = "";
	public static final String RVC_CMD_PARAM_UNLOCK_DOORS = "door";
	public static final String RVC_CMD_PARAM_WINDOW_SUNROOF = "sunroof";
	public static final String RVC_CMD_PARAM_WINDOW_DRIVER = "driver";
	public static final String RVC_CMD_PARAM_WINDOW_PASSENGER = "passenger";
	public static final String RVC_CMD_PARAM_WINDOW_LHR = "lhr";
	public static final String RVC_CMD_PARAM_WINDOW_RHR = "rhr";
	public static final String RVC_CMD_PARAM_WINDOW_POSITION = "position";
	public static final String RVC_CMD_PARAM_KEY_REQ_TYPE = "key_req_type";
	public static final String RVC_CMD_PARAM_KEY_ID = "key_id";
	public static final String RVC_CMD_PARAM_ENGINE_REQ_TYPE = "";
	public static final String RVC_CMD_PARAM_HEATED_SEAT_DRIVER = "heated_seat_driver";
	public static final String RVC_CMD_PARAM_HEATED_SEAT_PASSENGER = "heated_seat_passenger";
	public static final String RVC_CMD_PARAM_CLIMATE_REQ_TYPE = "climate_req_type";
	public static final String RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP = "climate_target_temp";
	public static final String RVC_CMD_PARAM_CANCEL = "cancel";
	public static final Map<String, Integer> RVC_CMD_PARAM_ID = new TreeMap<>();
	static {
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_FMC_LIGHTS, 1);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_FMC_HORN, 2);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_FMC_LENGTH, 3);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_UNLOCK_SILENT_FLAG, 4);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER, 5);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_UNLOCK_PM_OVERRIDE, 6);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_UNLOCK_DOORS, 7);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_WINDOW_SUNROOF, 8);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_WINDOW_DRIVER, 9);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_WINDOW_PASSENGER, 10);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_WINDOW_LHR, 11);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_WINDOW_RHR, 12);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_WINDOW_POSITION, 13);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_KEY_REQ_TYPE, 14);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_KEY_ID, 15);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_ENGINE_REQ_TYPE, 16);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_HEATED_SEAT_DRIVER, 17);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_HEATED_SEAT_PASSENGER, 18);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_CLIMATE_REQ_TYPE, 19);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP, 20);
		RVC_CMD_PARAM_ID.put(RVC_CMD_PARAM_CANCEL, 255);
	}
	public static final Map<String, String> RVC_CMD_PARAM_VALUE = new TreeMap<>();
	static {
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LIGHTS+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LIGHTS+"_false", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_HORN+"_off", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_HORN+"_low", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_HORN+"_high", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_30", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_60", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_90", "03");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_120", "04");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_150", "05");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_180", "06");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_210", "07");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_240", "08");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_270", "09");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_FMC_LENGTH+"_300", "0A");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SILENT_FLAG+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SILENT_FLAG+"_false", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_1", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_2", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_3", "03");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_4", "04");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_5", "05");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_6", "06");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_7", "07");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_8", "08");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_9", "09");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_10", "0A");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_11", "0B");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_12", "0C");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_13", "0D");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_14", "0E");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_SELENT_TIMER+"_15", "0F");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_DOORS+"_boot", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_UNLOCK_DOORS+"_all", "03");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_SUNROOF+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_SUNROOF+"_false", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_DRIVER+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_DRIVER+"_false", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_PASSENGER+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_PASSENGER+"_false", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_LHR+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_LHR+"_false", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_RHR+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_RHR+"_false", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_POSITION+"_0", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_POSITION+"_10", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_POSITION+"_50", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_WINDOW_POSITION+"_100", "03");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_KEY_REQ_TYPE+"_enable", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_KEY_REQ_TYPE+"_disable", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_DRIVER+"_off", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_DRIVER+"_on", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_DRIVER+"_low", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_DRIVER+"_mid", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_DRIVER+"_high", "03");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_PASSENGER+"_off", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_PASSENGER+"_on", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_PASSENGER+"_low", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_PASSENGER+"_mid", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_HEATED_SEAT_PASSENGER+"_high", "03");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_REQ_TYPE+"_blower", "00");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_REQ_TYPE+"_ac", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_16", "02");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_17", "03");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_18", "04");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_19", "05");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_20", "06");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_21", "07");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_22", "08");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_23", "09");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_24", "0A");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_25", "0B");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_26", "0C");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_27", "0D");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CLIMATE_CLIMATE_TARGET_TEMP+"_28", "0E");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CANCEL+"_true", "01");
		RVC_CMD_PARAM_VALUE.put(RVC_CMD_PARAM_CANCEL+"_false", "00");
	}

	public static final String AID_CONFIGURATION = "100";
	public static final String AID_RVC = "111";
	public static final String AID_JOURNEY = "112";
	public static final String AID_STATUS = "113";
	public static final String AID_SVT = "114";
	public static final String AID_REMOTE_KEY = "115";
	public static final String AID_DIAGNOSTIC = "801";
	public static final String AID_ECALL = "902";
	public static final String AID_BCALL = "903";
	public static final String AID_ICALL = "904";

	public static final String MSG_KEY_ALARM = "";
}
