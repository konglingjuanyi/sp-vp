package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 安防 远程控制服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-17 14:25
 * modify date 2015-8-7 14:05
 * @version 0.6, 2015-8-7
 */
@Transactional
public class RvcApiImplTest extends BaseServiceTestCase {

    @Autowired
    private IRvcApi rvcApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testRequestControl() throws Exception {
        String command = "find_my_car";
        String requestClient = "mobile";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("light", true);
        parameters.put("horn", "low");
        parameters.put("horn_length", 30);
        Long controlCommandId =  rvcApi.requestControl(requestClient, userId, vin, command, parameters);
        Assert.assertNotNull(controlCommandId);
    }

    @Test
    @Rollback(false)
    public void testCancelControl() throws Exception {
        String command = "find_my_car";
        String requestClient = "mobile";
        rvcApi.cancelControl(requestClient, userId, vin, command);
    }

    @Test
    @Rollback(false)
    public void testUpdateControlStatus() throws Exception {
        Integer mid = 2;
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_RVC, mid);
        otaDto.setEventId(241L);
        String rvcStatus = "00";
        VehiclePosDto vehiclePosDto =  new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        vehicleStatusDtos.add(new VehicleStatusDto("status", 1));
        rvcApi.updateControlStatus(otaDto, BinaryAndHexUtil.hexStringToByte(rvcStatus),
                null, vehiclePosDto, vehicleStatusDtos);
    }

}
