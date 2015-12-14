/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 * 2015-08-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.SvtApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.api.ISvtApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.*;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt.StolenAlarmDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import com.zxq.iov.cloud.sp.vp.service.*;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 安防服务 被盗追踪API实现类
 */
@Service
public class SvtApiImpl extends BaseApi implements ISvtApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(SvtApiImpl.class);

	@Autowired
	private ISvtService svtService;
	@Autowired
	private IEventService eventService;
	@Autowired
	private IEventDefinitionService eventDefinitionService;
	@Autowired
	private IEventInstanceService eventInstanceService;
	@Autowired
	private IVehicleInfoService vehicleInfoService;
	@Override
	public void requestAlarm(String vin) throws ServLayerException {
		AssertRequired("vin", vin);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_SVT, 13);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto);
	}

	@Override
	public void alarm(OtaDto otaDto, List<StolenAlarmDto> stolenAlarmDtos) throws ApiException {
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			StolenAlarmDtoAssembler stolenAlarmDtoAssembler = new StolenAlarmDtoAssembler();
			VehiclePosDtoAssembler posDtoAssembler = new VehiclePosDtoAssembler();
			for (StolenAlarmDto stolenAlarmDto : stolenAlarmDtos) {
				svtService.alarm(getTboxById(otaDto.getTboxId()), stolenAlarmDtoAssembler.fromDto(stolenAlarmDto),
						posDtoAssembler.fromDto(stolenAlarmDto.getVehiclePosDto()), event.getId());
			}
			Map<String, Object> params = new HashMap<>();
			params.put("svt_alert", 1);
			params.put("svt_alert_msg", "");
			pushMobile(getOwnerIdByVin(otaDto.getVin()), "车辆出现报警状态", "车辆出现报警状态", params);
			eventService.end(event);
		}
	}

	@Override
	public void updateTrack(OtaDto otaDto, List<TrackDto> trackDtos) throws ServLayerException {
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
			VehicleStatusDtoAssembler statusDtoAssembler = new VehicleStatusDtoAssembler();
			VehiclePosDtoAssembler posDtoAssembler = new VehiclePosDtoAssembler();
			for (TrackDto trackDto : trackDtos) {
				vehicleStatusDtos.add(new VehicleStatusDto("gnssSpeed", trackDto.getGnssSpeed()));
				vehicleStatusDtos.add(new VehicleStatusDto("gsmAntConnected", trackDto.isGsmAntConnected() ? 1 : 0));
				vehicleStatusDtos.add(new VehicleStatusDto("gnssAntConnected", trackDto.isGnssAntConnected() ? 1 : 0));
				vehicleStatusDtos.add(new VehicleStatusDto("vehicleBatteryConnected",
						trackDto.isVehicleBatteryConnected() ? 1 : 0));
				vehicleStatusDtos.add(new VehicleStatusDto("intBattV", trackDto.getIntBattV()));
				vehicleStatusDtos.add(new VehicleStatusDto("vehicleAlarmStatus", trackDto.getVehicleAlarmStatus()));
				vehicleStatusDtos.add(new VehicleStatusDto("engineStatus", trackDto.getEngineStatus()));
				vehicleStatusDtos.add(new VehicleStatusDto("powerMode", trackDto.getPowerMode()));
				vehicleStatusDtos.add(new VehicleStatusDto("lastKeySeen", trackDto.getLastKeySeen()));
				vehicleStatusDtos.add(new VehicleStatusDto("fuelLevelPrc", trackDto.getFuelLevelPrc()));
				vehicleStatusDtos.add(new VehicleStatusDto("fuelRange", trackDto.getFuelRange()));
				vehicleStatusDtos.add(new VehicleStatusDto("canBusActive", trackDto.isCanBusActive() ? 1 : 0));
				vehicleStatusDtos.add(new VehicleStatusDto("lastCanBusActiveityTime",
						(int) (trackDto.getLastCanBusActivityTime().getTime() / 1000)));
				vehicleStatusDtos.add(new VehicleStatusDto("ttnTrackPoint", trackDto.getTtnTrackPoint()));
				svtService
						.updateTrack(getTboxById(otaDto.getTboxId()), statusDtoAssembler.fromDtoList(vehicleStatusDtos),
								posDtoAssembler.fromDto(trackDto.getVehiclePosDto()), event.getId());
				vehicleStatusDtos.clear();
			}
			eventService.end(event);
		}
	}

	@Override
	public void requestTrackSetting(String vin, Integer trackInterval, Integer tracks) throws ServLayerException {
		AssertRequired("vin", vin);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_SVT, 3);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto, new TrackSettingReqDto(trackInterval, tracks));
	}

	@Override
	public void requestSingleTrack(String vin) throws ServLayerException {
		AssertRequired("vin", vin);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_SVT, 4);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto);
	}

	@Override
	public void requestCloseAlarm(String vin) throws ServLayerException {
		AssertRequired("vin", vin);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_SVT, 5);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto);
	}

	@Override
	public void responseCloseAlarm(OtaDto otaDto, Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos)
			throws ServLayerException {
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			if (allAlarmClosed) {
				eventService.end(event);
			} else {
				eventService.error(getTboxById(otaDto.getTboxId()).getVin(), getCode(otaDto), 1, event.getId());
			}
		}
	}

	@Override
	public void requestAuthKey(String vin, Integer keyId) throws ServLayerException {
		AssertRequired("vin,keyId", vin, keyId);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_SVT, 7);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto, new AuthKeyReqDto(keyId));
	}

	@Override
	public void responseAuthKey(OtaDto otaDto, Boolean keyIsAccepted, Integer failureReason) throws ServLayerException {
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
	}

	@Override
	public void requestImmobilise(String vin, Integer immoStatus) throws ServLayerException {
		AssertRequired("vin,immoStatus", vin, immoStatus);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_SVT, 9);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto, new ImmobiliseReqDto(immoStatus));
	}

	@Override
	public void responseImmobilise(OtaDto otaDto, Integer immoStatus, Integer failureReason) throws ServLayerException {
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
	}

	@Override
	public void requestUpdateProtectStrategy(String vin, Date startTime, Date endTime,
			List<ProtectStrategySettingDto> protectStrategySettingDtos) throws ServLayerException {
		AssertRequired("vin", vin);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_SVT, 11);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto, new UpdateProtectStrategyReqDto(startTime, endTime, protectStrategySettingDtos));
	}

	@Override
	public void responseUpdateProtectStrategy() {
		// 此处输入有问题，待确认
	}

	@Override
	public StolenStatusDto getStolenStatus(String vin) throws ApiException {
		AssertRequired("vin", vin);
		boolean isStolen=false;
		Map<String,Object> parmMap=new HashMap();
		parmMap.put("tboxId",getTboxByVin(vin).getTboxId());
		List<StolenAlarm> stolenAlarmList=svtService.findStolenAlarmByMap(parmMap);
		List<StolenAlarmDto> stolenAlarmDtos=new ArrayList<>();
		if(stolenAlarmList!=null && stolenAlarmList.size()>0){
			isStolen=true;
			for(StolenAlarm stolenAlarm:stolenAlarmList){
				StolenAlarmDto stolenAlarmDto=new StolenAlarmDto();
				stolenAlarmDto.setAlarmData(stolenAlarm.getAlarmData());
				stolenAlarmDto.setAlarmTime(stolenAlarm.getAlarmTime());
				stolenAlarmDto.setAlarmTypeId(stolenAlarm.getAlarmType());
				//stolenAlarmDto其它字段赋值待确认
				stolenAlarmDtos.add(stolenAlarmDto);
			}
		}
		StolenStatusDto stolenStatusDto=new StolenStatusDto();
		stolenStatusDto.setStolenAlarmDtos(stolenAlarmDtos);
		stolenStatusDto.setStolen(isStolen);
		return stolenStatusDto;
	}

	@Override
	public List<TrackDto> listVehicleLastTrack(String vin, int trackCount) throws ApiException {
		AssertRequired("vin", vin);
		long ownerId=getOwnerIdByVin(vin);
		EventDefinition eventDefinition=eventDefinitionService.findEventDefinitionById(8L);
		long eventDefinitionId=eventDefinition.getId();
		List<EventInstance> eventInstances=eventInstanceService
				.listEventInstanceByEventDefinitionId(eventDefinitionId, ownerId,Integer.valueOf(1));
		EventInstance eventInstance=new EventInstance();
		if(eventInstances!=null && eventInstances.size()>0){
			eventInstance=eventInstances.get(0);
		}
		long eventId=eventInstance.getId();
		List<VehicleInfo> vehicleInfos=vehicleInfoService.listVehicleInfoByEventId(eventId);
		List<TrackDto> trackDtos=new ArrayList();
		if(vehicleInfos!=null && vehicleInfos.size()>0){
			for(VehicleInfo vehicleInfo:vehicleInfos){
				TrackDto trackDto=new TrackDto();
				VehiclePos vehiclePos=vehicleInfo.getVehiclePos();
				VehiclePosDto vehiclePosDto= new VehiclePosDtoAssembler().toDto(vehiclePos);
				trackDto.setVehiclePosDto(vehiclePosDto);
				//trackDto其它字段的赋值待确认
				trackDtos.add(trackDto);
			}
		}
		return trackDtos;
	}
}