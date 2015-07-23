package com.zxq.iov.cloud.sp.vp.common;

import java.util.Map;
import java.util.TreeMap;

/**
 * 安防 常量类
 *
 * @author 叶荣杰
 * create date 2015-6-12 11:31
 * modify date 2015-7-23 14:45
 * @version 0.11, 2015-7-23
 */
public class Constants {

	public static final String QUEUE_ROUTING_KEY = "AppTBOXServiceQueue.AV1.0";

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
	public static final String RVC_CMD_HEATED_SEAT_CONTROL = "headed_seat_control";
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
