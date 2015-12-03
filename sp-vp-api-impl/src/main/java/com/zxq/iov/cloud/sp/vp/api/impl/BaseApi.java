/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-24       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.2           增加检查车主的成员函数
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.BaseApi
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.logger.support.InfoData;
import com.zxq.iov.cloud.sp.mds.tcmp.api.ITboxApi;
import com.zxq.iov.cloud.sp.mds.tcmp.api.IVehicleApi;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.TboxDto;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.VehicleDto;
import com.zxq.iov.cloud.sp.msg.api.IMessageApi;
import com.zxq.iov.cloud.sp.msg.api.dto.BaiduMessageDto;
import com.zxq.iov.cloud.sp.vp.api.ServiceMessage;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.common.constants.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 基础接口类
 */
public class BaseApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseApi.class);

	@Autowired
	private ITboxApi tboxApi;
	@Autowired
	private IVehicleApi tcmpVehicleApi;
	@Autowired
	private IMessageApi messageApi;

	@Resource(name = "tboxAppServiceTemplate")
	private RabbitTemplate tboxAppServiceTemplate;

	protected void sendQueue(OtaDto otaDto) {
		sendQueue(otaDto, null);
	}

	protected void sendQueue(OtaDto otaDto, Object appData) {
		sendQueue(otaDto, appData, null);
	}

	protected void sendQueue(OtaDto otaDto, Object appData, String messageKey) {
		ServiceMessage serviceMessage = new ServiceMessage(otaDto);
		if (StringUtils.isNotEmpty(messageKey)) {
			serviceMessage.setMessageKey(messageKey);
		}
		try {
			if (null != appData) {
				serviceMessage.setAppData(JSON.json(appData));
			}
			tboxAppServiceTemplate.convertAndSend(Constants.QUEUE_ROUTING_KEY, serviceMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected InfoData getInfoData() {
		InfoData infoData = new InfoData();

		return infoData;
	}

	/**
	 * 根据TBOXID得到TBOX对象
	 *
	 * @param tboxId TBOX ID
	 * @return TBOX对象
	 */
	protected Tbox getTboxById(Long tboxId) throws ServLayerException {
		TboxDto tboxDto = new TboxDto();
		tboxDto.setId(tboxId);
		return getTboxByTboxDto(tboxDto);
	}

	/**
	 * 根据VIN得到TBOX对象
	 *
	 * @param vin 车辆唯一码
	 * @return TBOX对象
	 */
	protected Tbox getTboxByVin(String vin) throws ServLayerException {
		TboxDto tboxDto = new TboxDto();
		tboxDto.setVin(vin);
		Tbox tbox = getTboxByTboxDto(tboxDto);
		if(null == tbox) {
			throw new ServLayerException(ExceptionConstants.VIN_NOT_EXIST);
		}
		return tbox;
	}

	/**
	 * 根据TBOX ID得到TBOX DTO
	 *
	 * @param tboxId TBOX ID
	 * @return TBOX DTO
	 */
	protected TboxDto getTboxDto(Long tboxId) {
		TboxDto tboxDto = new TboxDto();
		tboxDto.setId(tboxId);
		List<TboxDto> tboxDtos = tboxApi.findTbox(tboxDto);
		return tboxDtos.size() > 0 ? tboxDtos.get(0) : null;
	}

	/**
	 * 根据VIN得到车主用户ID
	 *
	 * @param vin 车辆唯一码
	 * @return 车主用户ID
	 */
	protected Long getOwnerIdByVin(String vin) throws ServLayerException {
		VehicleDto vehicleDto = tcmpVehicleApi.findVehicleByVin(vin);
		if(null == vehicleDto) {
			throw new ServLayerException(ExceptionConstants.VIN_NOT_EXIST);
		}
		return vehicleDto.getUserId();
	}

	/**
	 * 更新TBOX DTO
	 *
	 * @param tboxDto TBOX DTO
	 */
	protected void updateTbox(TboxDto tboxDto) {
		tboxApi.updateTboxInfo(tboxDto);
	}

	/**
	 * 根据DTO得到TBOX对象
	 *
	 * @param tboxDto TBOX DTO
	 * @return TBOX对象
	 */
	private Tbox getTboxByTboxDto(TboxDto tboxDto) throws ServLayerException {
		List<TboxDto> tboxDtos = tboxApi.findTbox(tboxDto);
		Tbox tbox = null;
		if (null != tboxDtos && tboxDtos.size() > 0) {
			tboxDto = tboxDtos.get(0);
			tbox = new Tbox(tboxDto.getId(), tboxDto.getVin(), getOwnerIdByVin(tboxDto.getVin()));
		}
		return tbox;
	}

	/**
	 * 验证输入参数必填项
	 *
	 * @param argNames
	 * @param args
	 * @throws ServLayerException
	 */
	protected void AssertRequired(String argNames, Object... args) throws ServLayerException {
		boolean isNull = false;
		String[] _argNames = argNames.split(",");
		StringBuffer argName = new StringBuffer("");
		int index = 0;
		for (Object arg : args) {
			if (null == arg) {
				argName.append(_argNames[index]);
				isNull = true;
			}
			index++;
		}
		if (isNull) {
			throw new ServLayerException(ExceptionConstants.PARAMS_IS_NULL,
					argName.toString() + " are not allowed to be null");
		}
	}

	protected String getCode(OtaDto otaDto) {
		return otaDto.getAid() + otaDto.getMid().toString();
	}

	/**
	 * 检查userId是否是VIN的车主
	 *
	 * @param vin    车辆唯一码
	 * @param userId 用户ID
	 * @throws ServLayerException
	 */
	protected void checkVinOwner(String vin, Long userId) throws ServLayerException {
		if (userId.longValue() != getOwnerIdByVin(vin).longValue()) {
			throw new ServLayerException(ExceptionConstants.USER_NOT_OWNER);
		}
	}

	/**
	 * 推送消息到用户手机
	 *
	 * @param userId  用户ID
	 * @param title   标题
	 * @param content 内容
	 * @param params  参数
	 * @throws ApiException
	 */
	protected void pushMobile(Long userId, String title, String content, Map<String, Object> params) throws ApiException {
		if (null != userId) {
			BaiduMessageDto messageDto = new BaiduMessageDto();
			messageDto.setUserId(userId);
			messageDto.setMsgType(0); // 0透传消息 1通知中心
			messageDto.setSendWay(2); // 1群发 2单发
			messageDto.setMsgTitle(title);
			messageDto.setMsgContent(content);
			messageDto.setCustomCnt(params);
			messageDto.setSendBy("安防");
			messageDto.setTopicCode("cgj04");
			messageDto.setVin("");
			messageApi.createBaiduMessage(messageDto);
		}
		else {
			LOGGER.warn("推送消息到用户手机失败，没有定位到用户信息");
		}
	}

}
