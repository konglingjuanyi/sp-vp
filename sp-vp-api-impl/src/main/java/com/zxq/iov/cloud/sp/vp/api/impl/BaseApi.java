package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.ServiceMessage;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 安防 基础服务类
 *
 * @author 叶荣杰
 * create date 2015-7-24 9:57
 * modify date 2015-8-6 14:42
 * @version 0.2, 2015-8-6
 */
public class BaseApi {

//    @Resource(name = "tboxAppServiceTemplate")
//    private RabbitTemplate tboxAppServiceTemplate;

    protected void sendQueue(OtaDto otaDto) {
        sendQueue(otaDto, null);
    }

    protected void sendQueue(OtaDto otaDto, Object appData) {
        sendQueue(otaDto, appData, null);
    }

    protected void sendQueue(OtaDto otaDto, Object appData, String messageKey) {
        ServiceMessage serviceMessage = new ServiceMessage(otaDto);
        if(StringUtils.isNotEmpty(messageKey)) {
            serviceMessage.setMessageKey(messageKey);
        }
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
        return 1L; // 此处根据VIN得到TBOX ID
    }

    /**
     * 验证输入参数必填项
     * @param argNames
     * @param args
     * @throws ServLayerException
     */
    protected void AssertRequired(String argNames, Object... args) throws ServLayerException {
        boolean isNull = false;
        String[] _argNames = argNames.split(",");
        StringBuffer argName = new StringBuffer("");
        int index = 0;
        for(Object arg : args) {
            if(null == arg) {
                argName.append(_argNames[index]);
                isNull = true;
            }
            index++;
        }
        if(isNull) {
            throw new ServLayerException(ExceptionConstants.PARAMS_IS_NULL,
                    argName.toString() + " are not allowed to be null");
        }
    }

    protected String getVin(OtaDto otaDto) {
        String vin = "11111111111111111";
        return vin;
    }

    protected String getCode(OtaDto otaDto) {
        return otaDto.getAid() + otaDto.getMid().toString();
    }

}
