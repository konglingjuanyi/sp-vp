/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-12       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.EcallApiImplTest
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 eCall API测试类
 */
@Transactional
public class EcallApiImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EcallApiImplTest.class);

    @Autowired
    private IEcallApi ecallApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartEcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 1);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallApi.startEcall(otaDto, vehiclePosDtos, 0, 1, 50, 60);
    }

    @Test
    @Rollback(false)
    public void testRequestEcallStatus() throws Exception {
        ecallApi.requestEcallStatus(vin);
    }

    @Test
    @Rollback(false)
    public void testUpdateEcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 4);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallApi.updateEcall(otaDto, vehiclePosDtos, 0, 1, 50, 60);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() throws Exception {
        ecallApi.requestHangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() throws Exception {
        String callNumber = "4008208888";
        ecallApi.requestCallBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 8);
        ecallApi.responseCallBack(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseEcall() throws Exception {
        ecallApi.requestCloseEcall(vin);
    }

    @Test
    @Rollback(false)
    public void testCloseEcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 9);
        ecallApi.closeEcall(otaDto);
    }

}
