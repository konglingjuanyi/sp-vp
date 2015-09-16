package com.zxq.iov.cloud.sp.vp.web;

import com.alibaba.dubbo.common.json.JSON;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.ServiceMessage;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 安防 队列测试类
 *
 * @author 叶荣杰
 * create date 2015-7-13 11:29
 * modify date
 * @version 0.1, 2015-7-13
 */
@Transactional
public class QueueTest extends BaseServiceTestCase {

    @Resource(name = "tboxAppServiceTemplate")
    private RabbitTemplate tboxAppServiceTemplate;

    protected void sendQueue(OtaDto otaDto) {
        sendQueue(otaDto, null);
    }

    protected void sendQueue(OtaDto otaDto, Object appData) {
        ServiceMessage serviceMessage = new ServiceMessage(otaDto);
        try {
            if(null != appData) {
                serviceMessage.setAppData(JSON.json(appData));
            }
            tboxAppServiceTemplate.convertAndSend(Constants.QUEUE_ROUTING_KEY, serviceMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Rollback(false)
    public void testRequestBcallStatus() {
        String vin = "1";
        OtaDto otaDto = new OtaDto(vin, Constants.AID_BCALL, 3);
        sendQueue(otaDto);
    }

}
