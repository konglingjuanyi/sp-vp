/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-19       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.TboxConfigApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sec.vowner.api.IVOwnerSecurityApi;
import com.zxq.iov.cloud.sp.mds.api.IZXQVehicleApi;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.TboxDto;
import com.zxq.iov.cloud.sp.vp.api.ITboxConfigApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.*;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.common.util.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.util.RSAUtils;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.ITboxConfigService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 远程配置API实现类
 */
@Service
public class TboxConfigApiImpl extends BaseApi implements ITboxConfigApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(TboxConfigApiImpl.class);

	@Autowired
	private ITboxConfigService tboxConfigService;
	@Autowired
	private IEventService eventService;
	@Autowired
	private IZXQVehicleApi zxqVehicleApi;
	@Autowired
	private IVOwnerSecurityApi vOwnerSecurityApi;

	@Override
	public void requestConfigUpdate(String vin) throws ServLayerException {
		AssertRequired("vin", vin);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_CONFIGURATION, 3);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto);
	}

	@Override
	public void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) throws ServLayerException {
		AssertRequired("isAccepted", isAccepted);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
	}

	// 这段实现是可优化的，但在协议里牵涉了业务异常，不清楚协议流程该怎么走
	@Override
	public TboxConfigDto checkConfigDelta(OtaDto otaDto, byte[] mcuVersion, byte[] mpuVersion, String vin, String iccid,
			byte[] configVersion, Integer configDelta) throws ApiException {
		AssertRequired("mcuVersion,mpuVersion,vin,iccid,configVersion,configDelta", mcuVersion, mpuVersion, vin, iccid,
				configVersion, configDelta);
		int packageCount = 0;
		boolean isVehicleBinded = false;
		// 检查绑定状态，如果未绑定则不做处理
		Integer activatedStatus = zxqVehicleApi.getVehicleActivatedStatus(vin, iccid);
		LOGGER.info("车辆:" + vin + "的绑定状态:" + activatedStatus);
		if (null != activatedStatus && activatedStatus > 0) {
			isVehicleBinded = true;
		}
		EventAssembler assembler = new EventAssembler();
		Event event = assembler.fromOtaDto(otaDto);
		eventService.start(event);
		TboxConfigDto tboxConfigDto = new TboxConfigDto();
		if (!event.isRetry()) {
			if (isVehicleBinded) {
				// 获取t-token，不应该在这里获取，等小彦确定
				String tToken = vOwnerSecurityApi.generateTtoken(vin);
				LOGGER.info("车辆:" + vin + "获取到T-TOKEN:" + tToken);
				// 然后这里应该把t-token下发给TBOX
				Integer lastConfigDelta = tboxConfigService
						.checkConfigDelta(otaDto.getTboxId(), mcuVersion, mpuVersion, vin, iccid, configVersion,
								configDelta, event.getId());
				if (lastConfigDelta > configDelta) {
					tboxConfigDto.setConfigDelta(lastConfigDelta);
				}
				//TODO 获取packageCount（邱铭杰）
			}
			tboxConfigDto.setPackageCount(packageCount);
			event.setResult(tboxConfigDto);
			eventService.end(event);
			otaDto.setMid(2);
			event = assembler.fromOtaDto(otaDto);
			eventService.start(event);
			eventService.end(event);
		} else {
			try {
				tboxConfigDto = JSON.parse(event.getResult().toString(), TboxConfigDto.class);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		tboxConfigDto.setEventId(event.getId());
		tboxConfigDto.setAid(otaDto.getAid());
		tboxConfigDto.setMid(otaDto.getMid());
		return tboxConfigDto;
	}

	@Override
	public TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) throws ServLayerException {
		AssertRequired("packageId,eventId", packageId, otaDto.getEventId());
		EventAssembler assembler = new EventAssembler();
		Event event = assembler.fromOtaDto(otaDto);
		eventService.start(event);
		TboxConfigPackageDto tboxConfigPackageDto = new TboxConfigPackageDto();
		if (!event.isRetry()) {
			// 此处读取缓存
			String key = otaDto.getEventId().toString();
			tboxConfigPackageDto.setPackageId(packageId);
			List<TboxConfigSettingDto> list = new ArrayList<>();
			// 此处假数据
			list.add(new TboxConfigSettingDto(1, BinaryAndHexUtil.hexStringToByte("01")));
			list.add(new TboxConfigSettingDto(2, BinaryAndHexUtil.hexStringToByte("02")));
			tboxConfigPackageDto.setTboxConfigSettingDtos(list);
			event.setResult(tboxConfigPackageDto);
			eventService.end(event);
			otaDto.setMid(7);
			event = assembler.fromOtaDto(otaDto);
			eventService.start(event);
			eventService.end(event);
		} else {
			try {
				tboxConfigPackageDto = JSON.parse(event.getResult().toString(), TboxConfigPackageDto.class);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		tboxConfigPackageDto.setEventId(event.getId());
		tboxConfigPackageDto.setAid(otaDto.getAid());
		tboxConfigPackageDto.setMid(otaDto.getMid());
		return tboxConfigPackageDto;
	}

	@Override
	public void closeConfigUpdate(OtaDto otaDto, Boolean result, byte[] mcuVersion, byte[] mpuVersion,
			byte[] configVersion, Integer configDelta) throws ServLayerException {
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
	}

	@Override
	public void requestReadConfig(String vin, Long[] tboxConfigsettingIds) throws ServLayerException {
		AssertRequired("vin", vin);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_CONFIGURATION, 8);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto, new ReadConfigReqDto(tboxConfigsettingIds));
	}

	@Override
	public void responseReadConfig(OtaDto otaDto, List<TboxConfigSettingDto> tboxConfigSettingDtos)
			throws ServLayerException {
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
	}

	@Override
	public KeyDto generateAsymmetricKey(OtaDto otaDto) throws ServLayerException {
		EventAssembler assembler = new EventAssembler();
		Event event = assembler.fromOtaDto(otaDto);
		eventService.start(event);
		KeyDto asymmetricKeyDto = new KeyDto();
		if (!event.isRetry()) {
			byte[] modulus = tboxConfigService.generateAsymmetricKey(otaDto.getTboxId());
			asymmetricKeyDto.setPublicKey(modulus);
			asymmetricKeyDto.setPublicKeyGranted(true);
			event.setResult(asymmetricKeyDto);
			eventService.end(event);
			otaDto.setMid(11);
			event = assembler.fromOtaDto(otaDto);
			eventService.start(event);
			eventService.end(event);
		} else {
			try {
				asymmetricKeyDto = JSON.parse(event.getResult().toString(), KeyDto.class);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		asymmetricKeyDto.setEventId(event.getId());
		asymmetricKeyDto.setAid(otaDto.getAid());
		asymmetricKeyDto.setMid(otaDto.getMid());
		return asymmetricKeyDto;
	}

	@Override
	public KeyDto bindTboxWithSecretKey(OtaDto otaDto, byte[] secretKeyWithEnc, byte[] tboxSnWithEnc)
			throws ServLayerException {
		EventAssembler assembler = new EventAssembler();
		Event event = assembler.fromOtaDto(otaDto);
		eventService.start(event);
		KeyDto keyDto = new KeyDto();
		if (!event.isRetry()) {
			RSAPrivateKey privateKey = tboxConfigService.getPrivateKey(otaDto.getTboxId());
			TboxDto tboxDto = getTboxDto(otaDto.getTboxId());
			String secretKey = null;
			String tboxSn = null;
			try {
				secretKey = RSAUtils.decryptByPrivateKey(new String(secretKeyWithEnc), privateKey);
				tboxSn = RSAUtils.decryptByPrivateKey(new String(tboxSnWithEnc), privateKey);
				if (tboxSn.equals(tboxDto.getTboxSn())) {
					tboxDto.setSecurityKey(secretKey);
					updateTbox(tboxDto);
					tboxConfigService.updateSecretKey(otaDto.getTboxId(), secretKey);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			keyDto.setEventId(event.getId());
			keyDto.setTboxId(otaDto.getTboxId());
			keyDto.setSecretKeyAccepted(true);
			event.setResult(keyDto);
			eventService.end(event);
			otaDto.setMid(13);
			event = assembler.fromOtaDto(otaDto);
			eventService.start(event);
			eventService.end(event);
		} else {
			try {
				keyDto = JSON.parse(event.getResult().toString(), KeyDto.class);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		keyDto.setEventId(event.getId());
		keyDto.setAid(otaDto.getAid());
		keyDto.setMid(otaDto.getMid());
		return keyDto;
	}
}