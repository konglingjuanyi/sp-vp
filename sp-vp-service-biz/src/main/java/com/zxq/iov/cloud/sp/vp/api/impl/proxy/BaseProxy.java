package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSON;
import com.zxq.iov.cloud.sp.vp.api.ServiceMessage;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.QueueUtil;

import java.io.IOException;

/**
 * 安防 基础代理类
 *
 * @author 叶荣杰
 * create date 2015-7-6 15:33
 * modify date
 * @version 0.1, 2015-7-6
 */
public class BaseProxy {

    protected void sendQueue(OtaDto otaDto) {
        sendQueue(otaDto, null);
    }

    protected void sendQueue(OtaDto otaDto, Object appData) {
        ServiceMessage serviceMessage = new ServiceMessage(otaDto);
        try {
            if(null != appData) {
                serviceMessage.setAppData(JSON.json(appData));
            }
            new QueueUtil().send(Constants.QUEUE_NAME, JSON.json(serviceMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
