/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-24       荣杰         1.0            Initial Version
 * 2015-08-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.DiagnosticApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.api.IDiagnosticApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic.DiagnosticDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.service.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防服务 远程诊断API实现类
 */
@Service
public class DiagnosticApiImpl extends BaseApi implements IDiagnosticApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticApiImpl.class);

	@Autowired
	private IDiagnosticService diagnosticService;
	@Autowired
	private IEventService eventService;

	@Override
	public void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) throws ServLayerException {
		AssertRequired("vin,diagnosticDtos", vin, diagnosticDtos);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_DIAGNOSTIC, 1);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			diagnosticService
					.requestDiagnostic(getTboxByVin(vin), new DiagnosticDtoAssembler().fromDtoList(diagnosticDtos));
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto);
	}

	@Override
	public void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) throws ServLayerException {
		AssertRequired("otaDto,diagnosticDtos", otaDto, diagnosticDtos);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			diagnosticService
					.responseDiagnostic(otaDto.getTboxId(), new DiagnosticDtoAssembler().fromDtoList(diagnosticDtos));
			eventService.end(event);
		}
	}
}