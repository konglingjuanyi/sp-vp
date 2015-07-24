package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSON;
import com.zxq.iov.cloud.sp.vp.api.ServiceMessage;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 安防 基础代理类
 *
 * @author 叶荣杰
 * create date 2015-7-6 15:33
 * modify date 2015-7-13 11:32
 * @version 0.3, 2015-7-13
 */
public class BaseProxy {

    @Autowired
    private ITboxDaoService tboxDaoService;

//    @Resource(name = "tboxAppServiceTemplate")
//    private RabbitTemplate tboxAppServiceTemplate;

    protected void sendQueue(OtaDto otaDto) {
        sendQueue(otaDto, null);
    }

    protected void sendQueue(OtaDto otaDto, Object appData) {
        ServiceMessage serviceMessage = new ServiceMessage(otaDto);
        try {
            if(null != appData) {
                serviceMessage.setAppData(JSON.json(appData));
            }
//            tboxAppServiceTemplate.convertAndSend(Constants.QUEUE_ROUTING_KEY, serviceMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Long getTboxId(String vin) {
        return tboxDaoService.findTboxIdByVin(vin);
    }

}
