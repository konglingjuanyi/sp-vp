package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 远程控制服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-17 14:25
 * modify date 2015-6-19 9:42
 * @version 0.3, 2015-6-19
 */
@Transactional
public class RvcServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("rvcServiceProxy")
    private IRvcService rvcService;

    @Test
    @Rollback(false)
    public void testRequestControl() {
        Long tboxId = 1L;
        Integer command = 6;
        //String parameter = "[{id:255, value:0}]";
        Long controlCommandId =  rvcService.requestControl(tboxId, command, null);
        Assert.assertNotNull(controlCommandId);
    }

    @Test
    @Rollback(false)
    public void testCancelControl() {
        Long controlCommandId = 44L;
        rvcService.cancelControl(controlCommandId);
    }

    @Test
    @Rollback(false)
    public void testResponseControl() {
        Long tboxId = 1L;
        Integer rvcStatus = 3;
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setEventId(88L);
        vehicleInfoDto.setTboxId(tboxId);
        vehicleInfoDto.setAid(Constants.AID_RVC);
        vehicleInfoDto.setMid(2);
        vehicleInfoDto.setEventCreateTime(new Date());
        rvcService.responseControl(rvcStatus, null, vehicleInfoDto);
    }

}
