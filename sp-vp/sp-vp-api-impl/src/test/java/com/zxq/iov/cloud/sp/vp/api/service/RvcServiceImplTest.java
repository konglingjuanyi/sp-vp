package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 安防 远程控制服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-17 14:25
 * modify date 2015-7-22 18:02
 * @version 0.5, 2015-7-22
 */
@Transactional
public class RvcServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("rvcServiceProxy")
    private IRvcService rvcService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestControl() throws Exception {
        Long userId = 1L;
        String command = "find_my_car";
        String requestClient = "mobile";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("light", true);
        parameters.put("horn", "low");
        parameters.put("horn_length", 30);
        Long controlCommandId =  rvcService.requestControl(requestClient, userId, vin, command, parameters);
        Assert.assertNotNull(controlCommandId);
    }

    @Test
    @Rollback(false)
    public void testCancelControl() throws Exception {
        Long userId = 1L;
        String command = "5";
        String requestClient = "mobile";
        rvcService.cancelControl(requestClient, userId, vin, command);
    }

    @Test
    @Rollback(false)
    public void testUpdateControlStatus() throws Exception {
        Integer mid = 2;
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_RVC, mid);
        otaDto.setEventId(128L);
        String rvcStatus = "3";
        VehiclePosDto vehiclePosDto =  new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        vehicleStatusDtos.add(new VehicleStatusDto("status", 1));
        rvcService.updateControlStatus(otaDto, BinaryAndHexUtil.hexStringToByte(rvcStatus),
                null, vehiclePosDto, vehicleStatusDtos);
    }

}
